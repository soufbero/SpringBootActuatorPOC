package com.poc.test.spring.boot.actuator.myhealth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class MyHealthCheck implements HealthIndicator {

    @Value("${probe.file.path}")
    private String probeFilePath;

    private File probeFile;

    @PostConstruct
    private void init(){
        probeFile = new File(probeFilePath);
    }

    @Override
    public Health health() {
        if (!probeFile.exists()){
            return Health.down().build();
        }
        return Health.up().build();
    }
}
