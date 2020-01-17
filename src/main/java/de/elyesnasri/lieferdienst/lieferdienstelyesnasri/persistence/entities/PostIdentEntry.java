package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.persistence.*;

@Entity
public class PostIdentEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postIdentId;
    private PersonalData personalData;
}
