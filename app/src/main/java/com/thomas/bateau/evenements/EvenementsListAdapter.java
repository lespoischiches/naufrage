package com.thomas.bateau.evenements;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thomas.bateau.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EvenementsListAdapter extends BaseAdapter {
    private List<Evenement> evenementList;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<IEvenementAdapterListener> listenerList=new ArrayList<>();

    public EvenementsListAdapter(Context context, List<Evenement> evenementList) {
        this.context=context;
        this.evenementList=evenementList;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return evenementList.size();
    }

    @Override
    public Object getItem(int position) {
        if(position < getCount()) {
            return evenementList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView=layoutInflater.inflate(R.layout.evenement_liste, parent, false);
        }
        TextView titreTextView=convertView.findViewById(R.id.evenement_liste_titre);
        ImageView iconImageView=convertView.findViewById(R.id.evenement_liste_photo);
        TextView descriptionTextView=convertView.findViewById(R.id.evenement_liste_description);

        Evenement evenement=(Evenement)getItem(position);

        titreTextView.setText(evenement.getTitle());
        iconImageView.setImageResource(evenement.getTypeUtilisateur().getIcon());
        descriptionTextView.setText(evenement.getDescription());

        convertView.setOnClickListener(v -> {
            listenerList.forEach(listener -> listener.onClickEvenement(evenement));
        });
        return convertView;
    }

    void addListener(IEvenementAdapterListener iEvenementAdapterListener) {
        listenerList.add(iEvenementAdapterListener);
    }
}
