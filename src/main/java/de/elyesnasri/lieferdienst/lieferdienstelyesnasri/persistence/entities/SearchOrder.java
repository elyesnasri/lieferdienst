package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.validation.constraints.NotEmpty;

public class SearchOrder {

    @NotEmpty(message = "Bitte geben Sie die Sendungsnummer ein.")
    private String parcelNumber;

    @NotEmpty(message = "Bitte geben Sie den Nachnamen des Empfängers ein.")
    private String lastName;

    @NotEmpty(message = "Bitte geben Sie die Postleitzhal ein.")
    private int plz;

    public String getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }
}
