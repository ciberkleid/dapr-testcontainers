package com.salaboy.dapr.write;

import com.salaboy.dapr.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

public class TestApp {

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(DaprTestConfiguration.class)
                .run(args);
    }

    @ImportTestcontainers(CommonContainers.class)
    static class DaprTestConfiguration {

    }

}
