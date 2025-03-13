package com.macross.server.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Data
@Component
@ConfigurationProperties(prefix = "macross")
public class properties {

    private String workDir;

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

}
