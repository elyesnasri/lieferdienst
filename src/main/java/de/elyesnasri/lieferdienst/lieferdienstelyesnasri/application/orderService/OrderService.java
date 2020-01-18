package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private final IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void sendParcel(Order order) {

        this.orderRepository.save(order);
        // TODO: call marco for payment
    }

    @Override
    public Optional<Order> getParcel(long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order;
    }

    @Override
    public Optional<Order> getParcelByNumber(String parcelNumber) {
        Optional<Order> order = this.orderRepository.findOrderByorderNumber(parcelNumber);
        return order;
    }
}
