package com.thomas.bateau.evenements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thomas.bateau.R;

import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementViewActivity extends AppCompatActivity {

    private Evenement evenement;
    private TextView textViewTitre, textViewDescription, textViewTexte;
    private ImageView image;
    private Button btnRetour, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement_view);
        textViewTitre=findViewById(R.id.activity_evenement_view_title);
        textViewDescription=findViewById(R.id.activity_evenement_view_description);
        textViewTexte=findViewById(R.id.activity_evenement_view_texte);
        btnRetour=findViewById(R.id.activity_evenement_view_retour);
        btnShare=findViewById(R.id.activity_evenement_view_share_btn);
        image=findViewById(R.id.activity_evenement_view_image);

        evenement=getIntent().getParcelableExtra(EVENEMENT);
        btnRetour.setOnClickListener(c -> {
            finish();
        });
        btnShare.setOnClickListener(c -> {
            String shareBody = "Je partage avec vous l'événement " + evenement.getTitle() + ":\n" + evenement.getDescription();
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, evenement.getTitle());
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Partager avec"));
        });

        textViewTitre.setText(evenement.getTitle());
        textViewDescription.setText(evenement.getDescription());
        textViewTexte.setText(evenement.getTexte());
        evenement.downloadImage(success -> {
            if(success) {
                image.setImageBitmap(evenement.getImage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        evenement.cancelDownloadImage();
    }
}