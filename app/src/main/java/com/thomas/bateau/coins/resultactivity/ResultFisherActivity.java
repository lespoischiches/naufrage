package com.thomas.bateau.coins.resultactivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.thomas.bateau.AdvancedSearchFragment;
import com.thomas.bateau.R;
import com.thomas.bateau.coins.ItemListView;
import com.thomas.bateau.coins.ItemListViewAdapter;
import com.thomas.bateau.reportActivity.spotActivity.SpotFragmentOneHand;

import com.thomas.bateau.spot.CommonSpotFragment;



import java.util.LinkedList;

public class ResultFisherActivity extends Activity {



    Button buttonBack ;
    boolean rechercheAvance = false;
    Fragment avance;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity_fisher);

        buttonBack = findViewById(R.id.result_activity_fisher_return);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinkedList<ItemListView> items = new LinkedList<ItemListView>();
        items.add(new ItemListView("Test", R.drawable.canne_icon, "C'est un test"));
        ItemListViewAdapter adapter = new ItemListViewAdapter(getApplicationContext(),R.layout.result_activity_fisher, items);
        ListView list = (ListView) findViewById(R.id.result_activity_listView);
        list.setAdapter(adapter);

        Button btn = findViewById(R.id.testfisher);
        avance = new AdvancedSearchFragment();
        btn.setText("Recherche avancé v");
        if(!rechercheAvance){
            btn.setText("Recherche avancé ^");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rechercheAvance) {
                    getFragmentManager().beginTransaction().replace(R.id.testfisherframe, avance).commit();
                }
                else{
                    getFragmentManager().beginTransaction().remove(avance).commit();
                }
                rechercheAvance = !rechercheAvance;
            }
        });
    }
}




