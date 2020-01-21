package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IDeliveryStatusRepository extends CrudRepository<DeliveryStatus, Long> {
    Optional<DeliveryStatus> findDeliveryStatusByName(String name);
}
