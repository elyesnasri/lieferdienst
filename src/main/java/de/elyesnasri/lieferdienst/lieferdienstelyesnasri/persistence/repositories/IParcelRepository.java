package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IParcelRepository extends CrudRepository<Parcel, Long> {
}
