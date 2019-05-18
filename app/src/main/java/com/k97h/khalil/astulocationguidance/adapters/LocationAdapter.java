package com.k97h.khalil.astulocationguidance.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView  = LayoutInflater.from(context).inflate(R.layout.location_list_name,parent,false);
            viewHolder.textView=convertView.findViewById(R.id.locationlistitem);
            viewHolder.favoriateitem=convertView.findViewById(R.id.favoriateitem);
            convertView.setTag(viewHolder);
        }else{

            viewHolder=(ViewHolder) convertView.getTag();
        }

        LocationData locationData=data.get(position);
        viewHolder.textView.setText(locationData.getName());
        viewHolder.favoriateitem.setTag(0);
        viewHolder.favoriateitem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                int i= (int) viewHolder.favoriateitem.getTag();
                if (i == 0) {
                    viewHolder.favoriateitem.setImageResource(R.drawable.ic_favorite);
                    viewHolder.favoriateitem.setTag(1);
                }else{
                    viewHolder.favoriateitem.setImageResource(R.drawable.ic_favorite_none);
                    viewHolder.favoriateitem.setTag(0);
                }
            }
        });
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClicked(position);
            }
        });

        Animation animation=AnimationUtils.loadAnimation(context,R.anim.slide_left);
        convertView.startAnimation(animation);

        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView favoriateitem;
    }
    public void setOnItemClickListener(LocationItemClickListener listener){
        this.itemClickListener=listener;
    }
}
