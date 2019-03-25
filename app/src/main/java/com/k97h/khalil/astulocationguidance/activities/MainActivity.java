package com.k97h.khalil.astulocationguidance.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.databases.DBhelper;
import com.k97h.khalil.astulocationguidance.fragments.FavoriteLocationFragment;
import com.k97h.khalil.astulocationguidance.fragments.HistoryLocationFragment;
import com.k97h.khalil.astulocationguidance.fragments.LocationFragment;
import com.k97h.khalil.astulocationguidance.interfaces.FragmentClickListener;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private  FavoriteLocationFragment favoriateLocationFragment;
    private LocationFragment locationFragment;
    private HistoryLocationFragment historyLocationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBhelper dBhelper = new DBhelper(this);

        File database = getApplicationContext().getDatabasePath(DBhelper.DBName);
        if (!database.exists()) {
            dBhelper.getReadableDatabase();
            copyDatabase(this);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locationFragment = LocationFragment.newInstance(dBhelper);
        favoriateLocationFragment = FavoriteLocationFragment.newInstance(dBhelper);
        historyLocationFragment = HistoryLocationFragment.newInstance(dBhelper);

        gotoFragment(locationFragment, true);
        favoriateLocationFragment.setOnFragmentListener(new FragmentClickListener() {
            @Override
            public void onClickItem(List<LocationData> data, int position) {
                Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        historyLocationFragment.setOnFragmentListener(new FragmentClickListener() {
            @Override
            public void onClickItem(List<LocationData> data, int position) {
                Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        locationFragment.setOnFragmentListener(new FragmentClickListener() {
            @Override
            public void onClickItem(List<LocationData> data, int position) {
                Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   gotoFragment(locationFragment,false);
                   return true;
                case R.id.navigation_dashboard:
                  gotoFragment(favoriateLocationFragment,false);
                  return true;
                case R.id.navigation_notifications:
                    gotoFragment(historyLocationFragment,false);
                    return true;
            }
            return false;
        }
    };


    private void copyDatabase(Context context) {
        try {
            InputStream inputStream=context.getAssets().open(DBhelper.DBName);
            String outfilename=DBhelper.DBLocation+DBhelper.DBName;
            OutputStream outputStream=new FileOutputStream(outfilename);
            byte[] buff= new byte[1024];
            int leng;
            while ((leng=inputStream.read(buff))>0){
                outputStream.write(buff,0,leng);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void gotoFragment(Fragment locationListFragment,boolean b) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, locationListFragment);
        if (!b)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.banks) {
            // Handle the camera action
        } else if (id == R.id.business) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

