package com.k97h.khalil.astulocationguidance.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.io.Serializable;
import java.util.List;

public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        final List<LocationData> data= (List<LocationData>) getIntent().getSerializableExtra("data");
        final int position =getIntent().getIntExtra("position",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setTitle(data.get(position).getName());
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView textView=findViewById(R.id.text_data);
        ImageView imageView=findViewById(R.id.images);
        Bitmap bmp= BitmapFactory.decodeByteArray(data.get(position).getImage(), 0 , data.get(position).getImage().length);
        imageView.setImageBitmap(bmp);
        textView.setText(data.get(position).getDescription());
        Button button=findViewById(R.id.gotomap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LocationDetailActivity.this,MapsActivity.class);
                intent.putExtra("latitude",  data.get(position).getLatitude());
                intent.putExtra("longitude",data.get(position).getLongitude());
                startActivity(intent);

            }
        });
    }
}
