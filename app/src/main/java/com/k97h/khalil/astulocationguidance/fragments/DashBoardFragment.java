package com.k97h.khalil.astulocationguidance.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.interfaces.FragmentdshClickListener;

public class DashBoardFragment extends Fragment {

    private FragmentdshClickListener mListener;
    private ImageView library,cafe,dorm,stadium,clinic,schools,classes;

    public DashBoardFragment() {

    }
    public static DashBoardFragment newInstance() {
        DashBoardFragment fragment = new DashBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        library=view.findViewById(R.id.library);
        cafe=view.findViewById(R.id.cafe);
        dorm=view.findViewById(R.id.dorm);
        schools=view.findViewById(R.id.school);
        classes=view.findViewById(R.id.clas);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                    mListener.onClickItem("library");
            }
        });
        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                    mListener.onClickItem("class");
            }
        });
        dorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                    mListener.onClickItem("dorm");
            }
        });
        schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                    mListener.onClickItem("school");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void setOnFragmentdshClickListener(FragmentdshClickListener listener){
        this.mListener=listener;
    }
}
