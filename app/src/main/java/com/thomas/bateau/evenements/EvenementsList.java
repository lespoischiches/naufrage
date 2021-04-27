package com.thomas.bateau.evenements;

import com.thomas.bateau.TypeUtilisateurs;

import java.util.ArrayList;

public class EvenementsList extends ArrayList<Evenement> {
    public EvenementsList() {
        super();
        Evenement e1=new Evenement("Espèce rare", "Un respésentant d'un espèce très rare vient d'être repéré.", TypeUtilisateurs.SCIENTIFIQUE, "https://vps-e690be27.vps.ovh.net/ihm/images/myspace.png");
        e1.setTexte("Un représentant d'une espèce en voie de disparition vient d'être découvert.\nIl s'agit là d'un étonnant spécimen disposant d'un compte Google+ et MySpace à la fois.\nLa dernière fois qu'un individu de ce type avait été observé à l'état sauvage remonte à 1956.");
        add(e1);
        Evenement e2=new Evenement("Troll perdu", "Un troll vient de se perdre", TypeUtilisateurs.PLONGEUR, "https://vps-e690be27.vps.ovh.net/ihm/images/troll.jpg");
        e2.setTexte("Un troll vient de se perdre. Aidez-nous à le retrouver SVP");
        add(e2);
        Evenement e3=new Evenement("Pingouin dans le desert", "Un pingouin a été retrouvé dans le désert Egyptien", TypeUtilisateurs.KITTER, "https://vps-e690be27.vps.ovh.net/ihm/images/pingouin.png");
        e3.setTexte("On vient de retrouver un pingouin complètement perdu dans le désert Egyptien. Il tenait entre ses plumes un glaçon à moitié fondu.");
        add(e3);
        Evenement e4=new Evenement("Lapin de jade sur la Lune", "Un lapin de jade a été vu sur la Lune", TypeUtilisateurs.PECHEUR, "https://vps-e690be27.vps.ovh.net/ihm/images/lapin.jpg");
        e4.setTexte("On vient d'apercevoir un lapin de jade sur la Lune. Il se trouvait avec une potion d'immortalité.");
        add(e4);
        Evenement e5=new Evenement("Complotiste sur le canal Slack", "Un complotiste de la COVID-19 a été vu sur Slack", TypeUtilisateurs.SKIPPER, "https://vps-e690be27.vps.ovh.net/ihm/images/seringue.png");
        e5.setTexte("Merci de nous faire savoir si vous avez davantage de renseignements à ce sujet");
        add(e5);
    }
}
