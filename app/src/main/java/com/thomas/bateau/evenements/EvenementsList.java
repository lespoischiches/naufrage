package com.thomas.bateau.evenements;

import com.thomas.bateau.TypeUtilisateurs;

import java.util.ArrayList;

public class EvenementsList extends ArrayList<Evenement> {
    public EvenementsList() {
        super();
        Evenement e1=new Evenement("Espèce rare", "Un respésentant d'un espèce très rare vient d'être repéré.", TypeUtilisateurs.SCIENTIFIQUE, "https://fr.seaicons.com/wp-content/uploads/2015/06/elephant-icon.png");
        e1.setTexte("Un représentant d'une espèce en voie de disparition vient d'être découvert.\nIl s'agit là d'un étonnant spécimen disposant d'un compte Google+ et MySpace à la fois.\nLa dernière fois qu'un individu de ce type avait été observé à l'état sauvage remonte à 1956.");
        add(e1);
    }
}
