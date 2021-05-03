package com.thomas.bateau.evenements;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.R;

public enum TypeEvenement {
    SPOT(BateauApplication.typeUtilisateurs.getIcon()),
    URGENCE(R.drawable.urgence_icon),
    DEBRIS(R.drawable.drawable_debris),
    AIDE(R.drawable.drawable_aide),
    METEO(R.drawable.drawable_meteo),
    NAUFRAGE(R.drawable.drawable_naufrage);

    private int associatedIcon;

    TypeEvenement(int icon) {
        this.associatedIcon=icon;
    }
    public int getIcon() {
        return this.associatedIcon;
    }
}
