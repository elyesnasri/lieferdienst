package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;

import java.util.Optional;

public interface IOrderService {
    void sendParcel(Order order);
    Optional<Order> getParcel (long id);
    Optional<Order> getParcelByNumber (String parcelNumber);
}
