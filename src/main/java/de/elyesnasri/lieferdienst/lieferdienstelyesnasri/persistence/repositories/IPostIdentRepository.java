package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IPostIdentRepository extends CrudRepository<PostIdentEntry, Long> {
//    Optional<PostIdentEntry> findPostIdentByPersonalData(String lastName);
    @Query(value = "SELECT s FROM PostIdentEntry s WHERE s.personalData.birthDate LIKE :birthDate")
    Optional<PostIdentEntry> findPostIdentByBirthDate(@Param("birthDate") Date birthDate);
}
