package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.parcelService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParcelService implements IParcelService {

    @Autowired
    private final IParcelRepository parcelRepository;

    public ParcelService(IParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public void saveParcel(Parcel parcel) {
        this.parcelRepository.save(parcel);
    }

    @Override
    public Iterable<Parcel> getAllParcels() {
        Iterable<Parcel> parcels = this.parcelRepository.findAll();
        return parcels;
    }

    @Override
    public Optional<Parcel> getParcel(int weight) {
        Optional<Parcel> parcel = this.parcelRepository.findParcelByWeight(weight);
        return parcel;
    }
}
