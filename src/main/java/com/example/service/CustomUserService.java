package com.example.service;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserRoleRel;
import com.example.exception.BusinessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleRelService userRoleRelService;

    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> byName = userService.findByName(username);
        if(byName==null||byName.size()<=0) {
            throw new BusinessException("用户不存在");
        }
        User user0 = byName.get(0);
        System.out.println(user0);
        List<UserRoleRel> userRoleRels = userRoleRelService.findbyUserId(user0.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(userRoleRels!=null&&userRoleRels.size()>0){
            for (UserRoleRel urr:userRoleRels) {
                Role role = roleService.find(urr.getRid());
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        User0 user01 = new User0(user0.getUsername(), user0.getPassword(), authorities);
        System.out.println(user01);
        return user01;
    }

    private class User0 extends org.springframework.security.core.userdetails.User{

        public User0(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

        public User0(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }
    }
}
