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

public class SpinnerAdapterYo extends ArrayAdapter<String> {

    private final Context context;
    private final String[] objects;
    private final TypedArray images;

    public SpinnerAdapterYo(Context context, String[] objects, TypedArray images) {
        super(context, R.layout.item_popup_list, objects);
        this.context = context;
        this.images = images;
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

        View row=inflater.inflate(R.layout.item_popup_list, parent, false);

        TextView label=(TextView)row.findViewById(R.id.itemName);
        label.setText(objects[position]);

        ImageView icon=(ImageView)row.findViewById(R.id.itemImage);
        icon.setImageResource(images.getResourceId(position, -1));

        return row;
    }
}