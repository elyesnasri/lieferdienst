package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PersonalData;

public class SendOrder {
//    private String recipientFirstName;
//    private String recipientLastName;
//    private String recipientStreet;
//    private int recipientPostalCode;
//    private String recipientCity;
//    private String recipientCountry;
//
//    private String senderFirstName;
//    private String senderLastName;
//    private String senderStreet;
//    private int senderPostalCode;
//    private String senderCity;
//    private String senderCountry;

    private PersonalData senderData;
    private PersonalData recipientData;

    public PersonalData getSenderData() {
        return senderData;
    }

    public void setSenderData(PersonalData senderData) {
        this.senderData = senderData;
    }

    public PersonalData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(PersonalData recipientData) {
        this.recipientData = recipientData;
    }

}
