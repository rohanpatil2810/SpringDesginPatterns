package rohan.it.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import rohan.it.dto.OrderEvent;

@Service
public class OrderService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    // Spring injects the KafkaTemplate bean here (Constructor Injection)
    public OrderService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderEvent event) {
        // topic name, key, value
        kafkaTemplate.send("order-events", event.getOrderId(), event);

        System.out.println(">>> PRODUCER sent: " + event);
    }
}