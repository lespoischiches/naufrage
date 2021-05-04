package com.thomas.bateau;

import androidx.annotation.NonNull;

public enum TypeUtilisateurs {
    SKIPPER("skipper", R.drawable.drawable_voilier),
    PECHEUR("pêcheur", R.drawable.drawable_canne),
    PLONGEUR("plongeur", R.drawable.drawable_plongeur),
    KITTER("kitter", R.drawable.drawable_kite),
    SCIENTIFIQUE("scientifique", R.drawable.drawable_microscope);


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
