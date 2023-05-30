package com.salaboy.dapr.write;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.State;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WriteController {

    @Value("${STATE_STORE_NAME:statestore}")
    private String STATE_STORE_NAME = "";

    @Value("${PUB_SUB_NAME:notifications-pubsub}")
    private String PUB_SUB_NAME  = "";
    @Value("${PUB_SUB_TOPIC:notifications}")
    private String PUB_SUB_TOPIC = "";

    private DaprClient client;

    @PostMapping("/")
    public MyValues storeValues(@RequestParam("message") String message) {
        State<MyValues> results = client.getState(STATE_STORE_NAME, "values", MyValues.class).block();

        MyValues valuesList = results.getValue();

        if (valuesList == null) {
            valuesList = new MyValues(new ArrayList<String>());
            valuesList.values().add(message);
        } else {
            valuesList.values().add(message);
        }
        System.out.println("Storing message: " + message);
        client.saveState(STATE_STORE_NAME, "values", valuesList).block();

        System.out.println("Publishing Event ( to "+PUB_SUB_NAME+" / "+ PUB_SUB_TOPIC +" ) with message: " + message);
        client.publishEvent(PUB_SUB_NAME, PUB_SUB_TOPIC, message).block();

        return valuesList;
    }

    @DeleteMapping("/")
    public void deleteAllValues() {
        client.deleteState(STATE_STORE_NAME, "values").block();
    }

    @PostConstruct
    void initDapr() {
        client = new DaprClientBuilder().build();
    }

}
