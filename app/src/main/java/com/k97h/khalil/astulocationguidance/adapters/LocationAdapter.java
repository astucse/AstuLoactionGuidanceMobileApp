package com.k97h.khalil.astulocationguidance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.k97h.khalil.astulocationguidance.R;
import com.k97h.khalil.astulocationguidance.interfaces.LocationItemClickListener;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.List;

public class LocationAdapter extends BaseAdapter {


    private Context context;
    private List<LocationData> data;
    private LocationItemClickListener itemClickListener;

    public LocationAdapter(Context context,List<LocationData> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView  = LayoutInflater.from(context).inflate(R.layout.location_list_name,parent,false);
            viewHolder.textView=convertView.findViewById(R.id.list_item);
            convertView.setTag(viewHolder);
        }else{

            viewHolder=(ViewHolder) convertView.getTag();
        }

        LocationData locationData=data.get(position);
        viewHolder.textView.setText(locationData.getName());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClicked(position);
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
    public void setOnItemClickListener(LocationItemClickListener listener){
        this.itemClickListener=listener;
    }
}
