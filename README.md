# Dispatch service with Kafka
This project is a small microservice on Spring Boot made with Apache Kafka. It can consume OrderCreated type of message, imitates its processing in DispatchService and send it to other Service [Tracking](https://github.com/DmitriiGoltsov/Tracking-service).

Kafka is configured to handle incorrect types of input (e.g. not JSON), does not produce "death pill", correctly works with Retryable (it is set to have 3 attempts and max with a proper backoff interval) and Not Retryable exceptions.

The code is tested with unit and integration test written with JUnit 5 and Mockito.

## Used technologies

+ Java 17
+ Apache Kafka
+ Spring Boot 
+ Apache Maven
+ JUnit 5 & Mockito
+ Others

## Requirements

1) Apache Kafka 2.13-3.6.1+

## How to run

1) Download the app with `git clone` command;
2) Run Kafka installed on your system;
3) Run Consumer and Producer with similar commands (bootstrap-server and other properties may vary):
```
./bin/kafka-console-consumer.sh --topic order.dispatched --bootstrap-server localhost:9092 --property print.key=true --property key.separator=:
```
and 
```
./bin/kafka-console-producer.sh --topic order.created --bootstrap-server localhost:9092 --property parse.key=true --property key.separator=:  
```
4) Run the app with this command: `mvn spring-boot:run`;
5) Test the app with CLI.


