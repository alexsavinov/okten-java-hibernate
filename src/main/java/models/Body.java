package models;

import lombok.ToString;

@ToString
public enum Body {
    MAHOGANY("Mahogany"), ALDER("Alder"), ASH("American ash");
    private final String text;

    Body(String text) {
        this.text = text;
    }
}
