package com.showorld.housestar.adapters;

/**
 * Created by Aman on 05-07-2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.showorld.housestar.R;

public class ListViewAdapterYo extends ArrayAdapter<String>{

    private final Context context;
    private final String[] items;
    private final TypedArray images;

    public ListViewAdapterYo(Activity context,
                             String[] items, TypedArray images) {
        super(context, R.layout.item_list, items);
        this.context = context;
        this.items = items;
        this.images = images;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView= inflater.inflate(R.layout.item_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.itemImage);

        txtTitle.setText(items[position]);
        imageView.setImageResource(images.getResourceId(position, -1));

        return rowView;
    }

}