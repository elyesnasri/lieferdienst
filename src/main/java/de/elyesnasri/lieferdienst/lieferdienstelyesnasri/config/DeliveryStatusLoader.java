package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.config;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Status;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IDeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliveryStatusLoader implements ApplicationRunner {

    @Autowired
    private final IDeliveryStatusRepository deliveryStatusRepository;

    public DeliveryStatusLoader(IDeliveryStatusRepository deliveryStatusRepository) {
        this.deliveryStatusRepository = deliveryStatusRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<DeliveryStatus> deliveryStatuses = new ArrayList<>();

        DeliveryStatus status1 = new DeliveryStatus();
        status1.setName(Status.Step1.getText());
        status1.setDescription("Die Daten der Sendung wurden erfolgreich übermittelt.");
        status1.setPercent("25");
        deliveryStatuses.add(status1);

        DeliveryStatus status2 = new DeliveryStatus();
        status2.setName(Status.Step2.getText());
        status2.setDescription("Die Sendung befindet sich auf dem Weg.");
        status2.setPercent("50");
        deliveryStatuses.add(status2);

        DeliveryStatus status3 = new DeliveryStatus();
        status3.setName(Status.Step3.getText());
        status3.setDescription("Die Sendung wurde zugestellt.");
        status3.setPercent("100");
        deliveryStatuses.add(status3);

        for (DeliveryStatus status: deliveryStatuses) {
            this.deliveryStatusRepository.save(status);
        }
    }
}
