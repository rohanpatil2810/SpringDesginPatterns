# Kafka Microservices - Order Producer & Notification Consumer

This folder contains two Spring Boot microservices demonstrating Kafka Producer-Consumer pattern.

## Services

| Service                          | Port | Role                          |
|----------------------------------|------|-------------------------------|
| KafkaOrderServiceProducer        | 8081 | Produces Order Events         |
| KafkaNotificationServiceConsumer | 8082 | Consumes Order Events & notifies |

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Apache Kafka 3.x / 4.x

---

## 1. Start Kafka from Scratch (Windows)

### Download Kafka
Download from: https://kafka.apache.org/downloads  
(Example used: `kafka_2.13-4.3.1`)

### Start Zookeeper (old versions) or KRaft (new versions)

**For Kafka 3.x / 4.x (KRaft mode - recommended):**

```bash
# Generate a Cluster UUID
bin\windows\kafka-storage.bat random-uuid

# Format the storage
bin\windows\kafka-storage.bat format -t <UUID> -c config\kraft\server.properties

# Start Kafka
bin\windows\kafka-server-start.bat config\kraft\server.properties
