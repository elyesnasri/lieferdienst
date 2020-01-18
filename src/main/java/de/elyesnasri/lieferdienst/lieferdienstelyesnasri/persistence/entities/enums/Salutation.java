package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums;

public enum Salutation {
    ANY("Keine Angabe"),
    MAN("Herr"),
    WOMEN("Frau");

    public final String text;

    Salutation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
