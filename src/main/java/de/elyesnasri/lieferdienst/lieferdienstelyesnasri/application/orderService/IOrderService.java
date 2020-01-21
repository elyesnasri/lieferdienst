package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;

import java.util.Optional;

public interface IOrderService {
    boolean sendOrder(Order order);
    Optional<Order> getOrder(long id);
    Optional<Order> getOrderByNumber(String orderNumber);
    void updateOrderStatus (Order order);
}
