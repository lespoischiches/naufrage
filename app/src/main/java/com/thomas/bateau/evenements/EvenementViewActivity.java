package com.thomas.bateau.evenements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.thomas.bateau.R;

import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementViewActivity extends AppCompatActivity {

    private Evenement evenement;
    private TextView textViewTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement_view);
        evenement=getIntent().getParcelableExtra(EVENEMENT);
        Log.d("A", evenement.getTitle());
        textViewTitre=findViewById(R.id.activity_evenement_view_title);
        textViewTitre.setText(evenement.getTitle());
    }
}