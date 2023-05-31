# Basic Dapr Write Application

This project contains a web service that will write messages to a statestore and also publish them to a pub-sub bus.

Start the application by running:
```shell
./mvnw clean spring-boot:test-run
```

This will start (or re-use) a set of containers on the local Docker daemon.

Send HTTP requests to the application using:
```shell
curl -X POST "http://localhost:8081?message=some-value"
```

It will respond with a JSON representation of all the values in the statestore, as the following listing shows:
```json
{"values":["some-value"]}
```
