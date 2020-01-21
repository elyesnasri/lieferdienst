package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums;

public enum Status {
    Step1 ("In Bearbeitung"),
    Step2("Wird zugestellt"),
    Step3("Zugestellt");

    public final String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
