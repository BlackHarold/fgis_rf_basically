package ru.bluewhale.fgis.enums;

public enum Volume {
    FERM("/ferm/"),
    FERMR("/fermr/"),
    FERP("/ferm/"),
    FERR("/fermr/"),
    GESN("/ferp/"),
    GESNM("/ferr/"),
    GESNMR("/gesn/"),
    GESNP("/gesnm/"),
    GESNR("/gesnmr/"),
    FSEM("/gesnp/"),
    FSSCM("/gesnr/"),
    FSSCO("/fsem/"),
    TCH("/fsscm/");

    String value;

    Volume(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
