package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;
import org.springframework.data.repository.CrudRepository;

public interface IPostIdentRepository extends CrudRepository<PostIdentEntry, Long> {
}
