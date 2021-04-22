package com.thomas.bateau;

import androidx.annotation.NonNull;

public enum TypeUtilisateurs {
    PECHEUR("pêcheur", R.drawable.canne_icon),
    SCIENTIFIQUE("scientifique", R.drawable.microscope_icon),
    SKIPPER("skipper", R.drawable.voilier_icon),
    PLONGEUR("plongeur", R.drawable.plongeur_icon),
    KITTER("kitter", R.drawable.kite_icon);

    private String typeStr;
    private int associatedIcon;

    TypeUtilisateurs(String str, int icon) {
        this.typeStr=str;
        this.associatedIcon=icon;
    }

    public String getString() {
        return this.typeStr;
    }

    public int getIcon() {
        return this.associatedIcon;
    }
}
