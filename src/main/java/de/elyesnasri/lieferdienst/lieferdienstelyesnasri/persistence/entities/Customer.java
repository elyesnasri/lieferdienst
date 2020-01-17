package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long customerId;
    private PersonalData personalData;

    public Customer() {
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
