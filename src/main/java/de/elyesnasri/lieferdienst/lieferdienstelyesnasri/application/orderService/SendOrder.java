package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PersonalData;

public class SendOrder {
    private PersonalData senderData;
    private PersonalData recipientData;
    private int parcelWeight;
    private String senderIban;
    private long senderAccountId;
    private String senderAccountPassword;

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

    public int getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(int parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public String getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String senderIban) {
        this.senderIban = senderIban;
    }

    public long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public String getSenderAccountPassword() {
        return senderAccountPassword;
    }

    public void setSenderAccountPassword(String senderAccountPassword) {
        this.senderAccountPassword = senderAccountPassword;
    }
}
