package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.config;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParcelsLoader implements ApplicationRunner {

    @Autowired
    private final IParcelRepository parcelRepository;

    public ParcelsLoader(IParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Parcel> parcels = new ArrayList<>();

        Parcel parcelS = new Parcel();
        parcelS.setType("S");
        parcelS.setPrice(3);
        parcelS.setLength(35);
        parcelS.setWidth(25);
        parcelS.setHeight(10);
        parcelS.setWeight(2);
        parcels.add(parcelS);

        Parcel parcelM = new Parcel();
        parcelM.setType("M");
        parcelM.setPrice(5);
        parcelM.setLength(60);
        parcelM.setWidth(30);
        parcelM.setHeight(15);
        parcelM.setWeight(5);
        parcels.add(parcelM);

        Parcel parcelL = new Parcel();
        parcelL.setType("L");
        parcelL.setPrice(7);
        parcelL.setLength(120);
        parcelL.setWidth(60);
        parcelL.setHeight(60);
        parcelL.setWeight(10);
        parcels.add(parcelL);

        for (Parcel parcel : parcels) {
            this.parcelRepository.save(parcel);
        }
    }
}
