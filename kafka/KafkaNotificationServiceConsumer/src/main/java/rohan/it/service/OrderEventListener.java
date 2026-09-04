package rohan.it.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import rohan.it.dto.OrderEvent;

@Service
public class OrderEventListener {

    // This method is called automatically whenever a message arrives
    @KafkaListener(
        topics = "order-events",           // which topic to listen to
        groupId = "notification-group"     // consumer group name
    )
    public void handleOrderEvent(OrderEvent event) {
        System.out.println(">>> CONSUMER received: " + event);
        System.out.println("Sending notification to: " + event.getCustomerEmail());
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("----------------------------------");
    }
}