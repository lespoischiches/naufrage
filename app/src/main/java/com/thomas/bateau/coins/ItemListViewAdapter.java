package com.thomas.bateau.coins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.thomas.bateau.R;

import java.util.LinkedList;

public class ItemListViewAdapter extends ArrayAdapter<ItemListView> {
    private final Context context;
    private LinkedList<ItemListView> items;

    public ItemListViewAdapter(Context context, int resource, LinkedList<ItemListView> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_recherche_spot_ou_club, parent, false);
        } else {
            convertView = (ConstraintLayout) convertView;
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setBackgroundResource(items.get(position).getImage());

        TextView viewTitle = (TextView) convertView.findViewById(R.id.titre);
        TextView viewDescription = (TextView) convertView.findViewById(R.id.description);
        viewTitle.setText(items.get(position).getNom());
        viewDescription.setText(items.get(position).getDescription());
        viewTitle.setTag(items.get(position).getNom());

        return convertView;
    }
}
