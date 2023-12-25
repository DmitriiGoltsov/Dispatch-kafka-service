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
