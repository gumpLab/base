package org.gumplab.mystarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gumplab")
public class StarterServiceProperties {

    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
