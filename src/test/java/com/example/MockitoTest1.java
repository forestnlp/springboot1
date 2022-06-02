package com.example;

import com.example.entity.User;
import com.example.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MockitoTest1 {

    @Test
    public void testMockito(){
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("1");
        when(mock.get(1)).thenReturn("2");
        Assertions.assertThat(mock.get(0)).isEqualTo("1");
        Assertions.assertThat(mock.get(1)).isEqualTo("2");
        System.out.println(mock.get(1));
    }

    @Test
    public void testMockito2(){
        UserService mock = mock(UserService.class);
        User user3 = new User();
        user3.setUsername("mock");
        user3.setId(3);
        user3.setPassword("mypass");
        when(mock.findbyId(3)).thenReturn(user3);
        User user = mock.findbyId(3);
        System.out.println(user);
    }
}
