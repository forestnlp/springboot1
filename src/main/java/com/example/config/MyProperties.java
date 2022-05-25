package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ai")
public class MyProperties {
    private String cnnModel;
    private String rnnModel;

    private int randomRange;

    public int getRandomRange() {
        return randomRange;
    }

    public void setRandomRange(int randomRange) {
        this.randomRange = randomRange;
    }

    public String getRandomUuid() {
        return randomUuid;
    }

    public void setRandomUuid(String randomUuid) {
        this.randomUuid = randomUuid;
    }

    private String randomUuid;

    public String getCnnModel() {
        return cnnModel;
    }

    public void setCnnModel(String cnnModel) {
        this.cnnModel = cnnModel;
    }

    public String getRnnModel() {
        return rnnModel;
    }

    public void setRnnModel(String rnnModel) {
        this.rnnModel = rnnModel;
    }
}
