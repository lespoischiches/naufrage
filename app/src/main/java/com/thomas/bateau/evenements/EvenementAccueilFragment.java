package com.thomas.bateau.evenements;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;

import java.util.stream.Collectors;

import static com.thomas.bateau.BateauApplication.eventsListURL;
import static com.thomas.bateau.BateauApplication.typeUtilisateurs;
import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementAccueilFragment extends android.app.Fragment implements IEvenementAdapterListener {

    private View fragView;
    private ListView listEvenementsView;
    private EvenementsList evenementsList=new EvenementsList();

    public EvenementAccueilFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.evenement_accueil_fragment, container, false);
        listEvenementsView=fragView.findViewById(R.id.frag_evenements_list);
        EvenementsListAdapter evenementsListAdapter=new EvenementsListAdapter(fragView.getContext(), evenementsList);
        listEvenementsView.setAdapter(evenementsListAdapter);
        evenementsListAdapter.addListener(this);
        evenementsList.downloadJSON(eventsListURL, (success) -> {
            if(success) {
                //evenementsList=evenementsList.stream().filter(e -> e.getTypeUtilisateur() == typeUtilisateurs).collect(Collectors.toCollection(EvenementsList::new));
                evenementsListAdapter.updateList(evenementsList);
            }
        });
        return fragView;
    }

    @Override
    public void onClickEvenement(Evenement evenement) {
        Log.d("A", "here");
        Intent intentEvenement=new Intent(fragView.getContext(), EvenementViewActivity.class);
        intentEvenement.putExtra(EVENEMENT, evenement);
        startActivity(intentEvenement);
    }

}
