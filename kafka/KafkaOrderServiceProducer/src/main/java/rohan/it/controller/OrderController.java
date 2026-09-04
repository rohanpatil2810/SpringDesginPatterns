package rohan.it.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rohan.it.dto.OrderEvent;
import rohan.it.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor Injection (best practice)
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderEvent event) {
        orderService.placeOrder(event);
        return "Order placed and event published to Kafka";
    }
}