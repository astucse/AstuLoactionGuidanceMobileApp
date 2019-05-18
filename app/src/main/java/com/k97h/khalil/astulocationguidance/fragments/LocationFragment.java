package com.k97h.khalil.astulocationguidance.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.activities.LocationDetailActivity;
import com.k97h.khalil.astulocationguidance.adapters.LocationAdapter;
import com.k97h.khalil.astulocationguidance.databases.DBhelper;
import com.k97h.khalil.astulocationguidance.interfaces.FragmentClickListener;
import com.k97h.khalil.astulocationguidance.interfaces.LocationItemClickListener;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.io.Serializable;
import java.util.List;

public class LocationFragment extends Fragment {

    private DBhelper dBhelper;
    private FragmentClickListener listener;
    private List<LocationData> data;
    private String category;

    public LocationFragment() {
    }

    public static LocationFragment newInstance(DBhelper dBhelper, String category) {
        LocationFragment fragment = new LocationFragment();
        fragment.dBhelper=dBhelper;
        fragment.category=category;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView mlistView=view.findViewById(R.id.locationlist);
        data=dBhelper.getLocationList(category);
        LocationAdapter locationAdapter=new LocationAdapter(getContext(),data);
        mlistView.setAdapter(locationAdapter);

        locationAdapter.setOnItemClickListener(new LocationItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getContext(), LocationDetailActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    public void setOnFragmentListener(FragmentClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
