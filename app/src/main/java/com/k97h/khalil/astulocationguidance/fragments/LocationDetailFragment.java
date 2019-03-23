package com.k97h.khalil.astulocationguidance.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.List;


public class LocationDetailFragment extends Fragment {

    private List<LocationData> data;
    private int position;

    public LocationDetailFragment() {
        // Required empty public constructor
    }

    public static LocationDetailFragment newInstance(List<LocationData> data,int position) {
        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.data=data;
        fragment.position=position;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView=view.findViewById(R.id.detail);
        TextView textView1=view.findViewById(R.id.title);
        textView1.setText(data.get(position).getName());
        textView.setText(data.get(position).getDescription());
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