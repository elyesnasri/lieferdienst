package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.persistence.*;

@Entity
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deliveryStatusId;
    private String name;
    private String description;
    private String percent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
