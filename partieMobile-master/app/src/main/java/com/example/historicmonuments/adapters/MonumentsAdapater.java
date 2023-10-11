package com.example.historicmonuments.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.historicmonuments.R;
import com.example.historicmonuments.activities.ZoneDetailActivity;
import com.example.historicmonuments.model.Monument;
import com.example.historicmonuments.model.Zone;

import org.w3c.dom.Text;

import java.util.List;

public class MonumentsAdapater extends BaseAdapter{


        List<Monument> monuments;
        Context context;
        TextView nameText;
        ImageView imageView;
        ImageView imageView2;
        Button viewButton;
        //TextView longitude;
        //TextView lattitude;

        public MonumentsAdapater(List<Monument> monuments, Context context) {
            this.monuments = monuments;
            this.context = context;
        }

        @Override
        public int getCount() {
            return monuments.size();
        }

        @Override
        public Object getItem(int position) {
            return monuments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return monuments.get(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view ==null){
                view= LayoutInflater.from(context).inflate(R.layout.monument_item,parent,false);
            }
            //longitude = view.findViewById(R.id.longitude);
            //lattitude = view.findViewById(R.id.lattitude);
            imageView = view.findViewById(R.id.imageView);
            nameText =view.findViewById(R.id.nameText);
            nameText.setText(monuments.get(position).getNom()+"\n"+Double.toString(monuments.get(position).getLongitude())+"\n"+monuments.get(position).getLattitude());
            //imageView2 = view.findViewById(R.id.imageView2);
            //longitude.setText(Double.toString(monuments.get(position).getLongitude()));
            //lattitude.setText(Double.toString(monuments.get(position).getLattitude()));
            Log.d("hhhhhhhhhhhhhhhhhh",monuments.get(position).getPhoto());
            Glide.with(context).load(monuments.get(position).getPhoto()).into(imageView);
            return view;
        }



    }


