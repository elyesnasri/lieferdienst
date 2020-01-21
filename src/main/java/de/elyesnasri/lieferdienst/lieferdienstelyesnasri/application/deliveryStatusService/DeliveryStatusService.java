package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.deliveryStatusService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IDeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryStatusService implements IDeliveryStatusService {

    @Autowired
    private final IDeliveryStatusRepository deliveryStatusRepository;

    public DeliveryStatusService(IDeliveryStatusRepository deliveryStatusRepository) {
        this.deliveryStatusRepository = deliveryStatusRepository;
    }

    @Override
    public Optional<DeliveryStatus> getDeliveryStatus(String name) {
        Optional<DeliveryStatus> status = this.deliveryStatusRepository.findDeliveryStatusByName(name);
        return status;
    }
}
