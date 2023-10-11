package com.example.historicmonuments.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.historicmonuments.R;
import com.example.historicmonuments.activities.VilleDetailActivity;
import com.example.historicmonuments.model.Ville;

import java.util.List;

public class VillesAdapter extends BaseAdapter {
    List<Ville> villes;
    Context context;
    TextView nameText;
    Button viewButton;

    public VillesAdapter(List<Ville> villes, Context context) {
        this.villes = villes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return villes.size();
    }

    @Override
    public Object getItem(int position) {
        return villes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return villes.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view ==null){
            view= LayoutInflater.from(context).inflate(R.layout.ville_item,parent,false);
        }

        nameText =view.findViewById(R.id.nameText);
        nameText.setText(villes.get(position).getNom());
        viewButton=view.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail(villes.get(position).getId());
            }
        });
        return view;
    }

    public void callDetail(int id){
        Intent intent = new Intent(context, VilleDetailActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
