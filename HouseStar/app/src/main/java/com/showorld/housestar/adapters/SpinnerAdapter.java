package com.showorld.housestar.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.showorld.housestar.R;

/**
 * Created by Aman on 07-07-2015.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] objects;

    public SpinnerAdapter(Context context, String[] objects) {
        super(context, R.layout.item_popup_list, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row=inflater.inflate(R.layout.item_noimage_list, parent, false);

        TextView label=(TextView)row.findViewById(R.id.itemName);
        label.setText(objects[position]);


        return row;
    }
}