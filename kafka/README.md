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

Create Topic
Bashbin\windows\kafka-topics.bat --create --topic order-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
Verify Topic
Bashbin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

2. Run the Microservices
Producer (Order Service)
Bashcd kafka/KafkaOrderServiceProducer
mvn spring-boot:run
→ Runs on http://localhost:8081
Consumer (Notification Service)
Bashcd kafka/KafkaNotificationServiceConsumer
mvn spring-boot:run
→ Runs on http://localhost:8082

3. Test the Flow
Send an order:
Bashcurl -X POST http://localhost:8081/orders \
  -H "Content-Type: application/json" \
  -d "{
    \"orderId\": \"ORD-1001\",
    \"customerEmail\": \"rohan@example.com\",
    \"amount\": 2499.50,
    \"status\": \"CREATED\"
  }"
Expected Output
Producer console:
text>>> PRODUCER sent: OrderEvent{orderId='ORD-1001', ...}
Consumer console:
text>>> CONSUMER received: OrderEvent{orderId='ORD-1001', ...}
Sending notification to: rohan@example.com

Architecture
textClient (curl)
    ↓
OrderController → OrderService → KafkaTemplate
    ↓
Kafka Topic: order-events
    ↓
@KafkaListener (notification-group)
    ↓
OrderEventListener → prints notification


Key Points Learned

JsonSerializer / JsonDeserializer for object ↔ JSON conversion
Consumer Group (notification-group)
auto-offset-reset: earliest
acks=all for durability
Type headers disabled → explicit spring.json.value.default.type required


Author
Rohan Patil
