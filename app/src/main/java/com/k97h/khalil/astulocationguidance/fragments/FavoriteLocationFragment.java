package com.k97h.khalil.astulocationguidance.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.adapters.FavoriateLocationAdapter;
import com.k97h.khalil.astulocationguidance.databases.DBhelper;
import com.k97h.khalil.astulocationguidance.interfaces.FragmentClickListener;
import com.k97h.khalil.astulocationguidance.interfaces.LocationItemClickListener;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.List;


public class FavoriteLocationFragment extends Fragment {

    private DBhelper dBhelper;
    private FragmentClickListener listener;
    private List<LocationData> data;
    public FavoriteLocationFragment() {
    }

    public static FavoriteLocationFragment newInstance(DBhelper dBhelper) {
        FavoriteLocationFragment fragment = new FavoriteLocationFragment();
        fragment.dBhelper=dBhelper;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favoriate_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView favoriatelist=view.findViewById(R.id.favlocationlist);
        data=dBhelper.getLocationList();
        FavoriateLocationAdapter favoriateLocationAdapter=new FavoriateLocationAdapter(getContext(),data);
        favoriatelist.setAdapter(favoriateLocationAdapter);
        favoriateLocationAdapter.setOnItemClickListener(new LocationItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if(listener!=null)
                    listener.onClickItem(data,position);
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
