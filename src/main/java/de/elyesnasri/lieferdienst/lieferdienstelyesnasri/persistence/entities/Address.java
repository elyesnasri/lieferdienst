package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @NotEmpty(message = "Bitte geben Sie eine Straße ein.")
    private String street;

    @NotNull(message = "Bitte geben Sie eine Postleitzahl ein.")
    private Integer postalCode;

    @NotEmpty(message = "Bitte geben Sie einen Ort ein.")
    private String city;

    @NotEmpty(message = "Bitte geben Sie ein Land ein.")
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
