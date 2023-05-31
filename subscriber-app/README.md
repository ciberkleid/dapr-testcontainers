# Basic Dapr Subscriber Application

This project contains a web service that will subscribe to messages in a pub-sub bus and write them to the log file.

This project assumes there are message in the pub-sub bus. You can use the Basic Dapr Write Application to write messages to the bus.

Start the application by running:
```shell
./mvnw clean spring-boot:test-run
```

This will start (or re-use) a set of containers on the local Docker daemon.

As messages are written to the bus, they will appear in the log file, as the following listing shows:
```text
2023-05-30T17:23:02.985-04:00  INFO 54853 --- [nio-8082-exec-9] c.s.dapr.subscribe.SubscribeController   : Message Received: some-value
```

TODO:
- Document how method naming conventions map to subscriber functionality
- Is statestore.yaml necessary in this project?
