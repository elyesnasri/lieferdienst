package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.parcelService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;

public interface IParcelService {
    void saveParcel (Parcel parcel);
    Iterable<Parcel> getAllPArcels ();
}
