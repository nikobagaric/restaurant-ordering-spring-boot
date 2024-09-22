package com.ogcs.nikob.RestaurantOrdering.service;

import com.ogcs.nikob.RestaurantOrdering.model.Order;
import com.ogcs.nikob.RestaurantOrdering.model.OrderItem;
import com.ogcs.nikob.RestaurantOrdering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Order} entities.
 * Provides methods to perform CRUD operations on orders.
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void sendOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            // mock-up sending to restaurant
            if (LocalDateTime.now().isAfter(order.getSentAt())) {
                System.out.println("Sending order to restaurant: " + order.getRestaurant().getName());
            } else {
                System.out.println("Order cannot be sent yet. Waiting until " + order.getSentAt());
            }
        } else {
            System.out.println("Order not found with ID: " + orderId);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void checkAndSendOrders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (LocalDateTime.now().isAfter(order.getSentAt())) {
                sendOrder(order.getId());
            }
        }
    }
}
