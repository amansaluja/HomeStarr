package com.showorld.housestar.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.showorld.housestar.R;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.model.BasketItem;

import java.util.List;

public class ProcedureGridAdapter extends ArrayAdapter<BasketItem> {

    private Context context;
    List<BasketItem> basketItems;
    private BasketItemDAO basketComplaintDAO;
    BasketItem basketItem;


    public ProcedureGridAdapter(Context context, List<BasketItem> basketItems) {
        super(context, R.layout.fragment_item_proc, basketItems);
        this.context = context;
        this.basketItems = basketItems;

    }

    private class ViewHolder {
        TextView basketItemName;
    }

    @Override
    public int getCount() {
        return basketItems.size();
    }

    @Override
    public BasketItem getItem(int position) {
        return basketItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_item_proc, null);
            holder = new ViewHolder();
            basketComplaintDAO = new BasketItemDAO(getContext());
            holder.basketItemName = (TextView) convertView
                    .findViewById(R.id.itemName);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final BasketItem basketItem = (BasketItem) getItem(position);
        holder.basketItemName.setText(basketItem.getItemName());


        if(basketItem.getItemName().trim().isEmpty()){
            holder.basketItemName.setVisibility(View.GONE);
        } else {
            holder.basketItemName.setText(basketItem.getItemName());
        }

        return convertView;
    }

}