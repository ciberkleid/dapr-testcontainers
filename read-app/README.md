# Basic Dapr Read Application

This project contains a web service that will read messages from a statestore and return them in an HTTP response.

This project assumes there are message in the statestore. You can use the Basic Dapr Write Application to write messages to the statestore.

Start the application by running:
```shell
mvn clean spring-boot:test-run
```

This will start (or re-use) a set of containers on the local Docker daemon.

Send HTTP requests to the application using:
```shell
curl "http://localhost:8080"
```

It will respond with a JSON representation of all the values in the statestore, as the following listing shows:
```json
{"values":["some-value","some-other-value"]}
```
