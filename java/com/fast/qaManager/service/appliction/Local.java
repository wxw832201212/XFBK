package com.fast.qaManager.service.appliction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fast.qaManager.service.core.*"})
@EnableJpaRepositories(basePackages = {"com.fast.qaManager.service.core.dao"})
@EntityScan(basePackages = {"com.fast.qaManager.service.core.entity"})
@EnableScheduling
public class Local {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(Local.class);
    }
}


