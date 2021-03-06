package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IOrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrderByorderNumber(String orderNumber);
}
