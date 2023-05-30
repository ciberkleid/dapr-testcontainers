package com.salaboy.dapr.javaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

public class TestApp {

    public static void main(String[] args) {
        System.out.println("inside test app");
        SpringApplication.from(JavaAppApplication::main)
                .with(DaprTestConfiguration.class)
                .run(args);
    }

    @ImportTestcontainers(CommonContainers.class)
    static class DaprTestConfiguration {

    }

}
