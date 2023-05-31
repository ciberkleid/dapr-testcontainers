package com.salaboy.dapr.read;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dapr.client.domain.State;

@RestController
public class ReadController {

	private static final Logger log = LoggerFactory.getLogger(ReadController.class);

	@Value("${STATE_STORE_NAME:statestore}")
	private String STATE_STORE_NAME = "";

	private DaprClient client = new DaprClientBuilder().build();

	@GetMapping("/")
	public MyValues readValues() {
		State<MyValues> results = client.getState(STATE_STORE_NAME, "values", MyValues.class).block();
		return results.getValue();
	}

}


