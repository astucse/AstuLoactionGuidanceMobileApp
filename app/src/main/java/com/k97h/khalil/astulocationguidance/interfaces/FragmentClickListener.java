package com.k97h.khalil.astulocationguidance.interfaces;

import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.List;

public interface FragmentClickListener {
    void onClickItem(List<LocationData> data,int position);
}
