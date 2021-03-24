package com.thomas.bateau;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class EvenementAccueilFragment extends Fragment {

    private View fragView;

    public EvenementAccueilFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.evenement_accueil_fragment, container, false);
        ListView listView=fragView.findViewById(R.id.frag_evenements_list);
        List<String> listItems=Arrays.asList("Hello", "World");
        listView.setAdapter(new ArrayAdapter<String>(fragView.getContext(), android.R.layout.simple_list_item_1, listItems));
        return fragView;
    }
}
