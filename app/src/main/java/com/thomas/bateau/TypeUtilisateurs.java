package com.thomas.bateau;

import androidx.annotation.NonNull;

public enum TypeUtilisateurs {
    PECHEUR("pêcheur"),
    SCIENTIFIQUE("scientifique"),
    SKIPPER("skipper"),
    PLONGEUR("plongeur"),
    KITTER("kitter");

    private String typeStr;

    TypeUtilisateurs(String str) {
        this.typeStr=str;
    }

    public String getString() {
        return this.typeStr;
    }
}
