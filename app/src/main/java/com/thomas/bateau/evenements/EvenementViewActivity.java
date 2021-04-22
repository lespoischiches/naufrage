package com.thomas.bateau.evenements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thomas.bateau.R;

import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementViewActivity extends AppCompatActivity {

    private Evenement evenement;
    private TextView textViewTitre, textViewDescription, textViewTexte;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement_view);
        textViewTitre=findViewById(R.id.activity_evenement_view_title);
        textViewDescription=findViewById(R.id.activity_evenement_view_description);
        textViewTexte=findViewById(R.id.activity_evenement_view_texte);
        btnRetour=findViewById(R.id.activity_evenement_view_retour);

        evenement=getIntent().getParcelableExtra(EVENEMENT);
        btnRetour.setOnClickListener(c -> {
            finish();
        });

        textViewTitre.setText(evenement.getTitle());
        textViewDescription.setText(evenement.getDescription());
        textViewTexte.setText(evenement.getTexte());
    }
}