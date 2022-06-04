package ru.bluewhale.fgis.enums;

public enum Unit {
    MACHINE("маш. ч."),
    PEOPLE("чел. ч.");

    String value;

    Unit(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
