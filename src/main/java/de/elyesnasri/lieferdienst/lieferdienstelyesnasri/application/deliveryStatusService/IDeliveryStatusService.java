package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.deliveryStatusService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;

import java.util.Optional;

public interface IDeliveryStatusService {
    Optional<DeliveryStatus> getDeliveryStatus (String name);
}
