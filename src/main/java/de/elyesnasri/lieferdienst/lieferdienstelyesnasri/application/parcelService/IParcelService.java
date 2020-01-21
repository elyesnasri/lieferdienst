package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.parcelService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;

import java.util.Optional;

public interface IParcelService {
    void saveParcel (Parcel parcel);
    Iterable<Parcel> getAllParcels();
    Optional<Parcel> getParcel(int weight);
}
