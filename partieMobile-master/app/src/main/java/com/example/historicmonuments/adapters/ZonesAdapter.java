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
import com.example.historicmonuments.activities.ZoneDetailActivity;
import com.example.historicmonuments.model.Zone;

import java.util.List;

public class ZonesAdapter extends BaseAdapter {

    List<Zone> zones;
    Context context;
    TextView nameText;
    Button viewButton;

    public ZonesAdapter(List<Zone> zones, Context context) {
        this.zones = zones;
        this.context = context;
    }

    @Override
    public int getCount() {
        return zones.size();
    }

    @Override
    public Object getItem(int position) {
        return zones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return zones.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view ==null){
            view= LayoutInflater.from(context).inflate(R.layout.zone_item,parent,false);
        }

        nameText =view.findViewById(R.id.nameText);
        nameText.setText(zones.get(position).getNom());
        viewButton=view.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail(zones.get(position).getId());
            }
        });
        return view;
    }

    public void callDetail(int id){
        Intent intent = new Intent(context, ZoneDetailActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
