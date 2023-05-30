package com.salaboy.dapr.write;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WriteControllerTests {

    @LocalServerPort
    private int localPort;

    @Test
    void canPostToEndpoint() {
        RestAssured.given().port(localPort).param("message", "foo")
                .when().post("/")
                .then().statusCode(200);

        RestAssured.given().port(localPort).param("message", "bar")
                .when().post("/")
                .then()
                .statusCode(200)
                .body("values", Matchers.contains("foo", "bar"));
    }

    @TestConfiguration
    @ImportTestcontainers(CommonContainers.class)
    static class ContainerConfig {

    }
}
