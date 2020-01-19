package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SearchOrder {
    private String orderNumber;

    private String lastName;

    private int plz;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
