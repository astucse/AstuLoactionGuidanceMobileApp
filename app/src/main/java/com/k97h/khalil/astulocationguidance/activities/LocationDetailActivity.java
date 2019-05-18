package com.k97h.khalil.astulocationguidance.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.models.LocationData;

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
        TextView textView=findViewById(R.id.text_data);
        TextView tit=findViewById(R.id.titledes);
        tit.setText(data.get(position).getName());
        ImageView imageView=findViewById(R.id.images);
        if(data.get(position).getImage()!=null){
            Bitmap bmp= BitmapFactory.decodeByteArray(data.get(position).getImage(), 0 , data.get(position).getImage().length);
            imageView.setImageBitmap(bmp);
        }


        textView.setText(data.get(position).getDescription());
        TextView gotoM=findViewById(R.id.gotomap);
        gotoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    Intent intent = new Intent(LocationDetailActivity.this, GoogleLocation.class);
                    intent.putExtra("latitude", data.get(position).getLatitude());
                    intent.putExtra("longitude", data.get(position).getLongitude());
                    startActivity(intent);
                }else{
                    Toast.makeText(LocationDetailActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

}
