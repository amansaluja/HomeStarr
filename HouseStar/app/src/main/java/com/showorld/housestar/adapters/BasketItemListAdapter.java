package com.showorld.housestar.adapters;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.activities.BasketItemListActivity;
//import com.showorld.housestar.activities.EditDetailsActivity;
//import com.showorld.housestar.activities.SelectCategoryActivity;
import com.showorld.housestar.activities.IngredientNameActivity;
import com.showorld.housestar.activities.IngredientTypeActivity;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.model.BasketItem;

import java.util.List;

public class BasketItemListAdapter extends ArrayAdapter<BasketItem> {

    private Context context;
    List<BasketItem> basketItems;
    private BasketItemDAO basketComplaintDAO;
    BasketItem basketItem;
    private ProgressDialog progressDialog;
    Boolean removedFromBasket = false;

    public BasketItemListAdapter(Context context, List<BasketItem> basketItems) {
        super(context, R.layout.fragment_item_list, basketItems);
        this.context = context;
        this.basketItems = basketItems;

    }

    private class ViewHolder {
        TextView basketItemName;
        TextView basketQuantity;
        ImageView basketRemove;
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
            convertView = inflater.inflate(R.layout.fragment_item_list, null);
            holder = new ViewHolder();
            basketComplaintDAO = new BasketItemDAO(getContext());
            holder.basketItemName = (TextView) convertView
                    .findViewById(R.id.iName);
            holder.basketQuantity = (TextView) convertView
                    .findViewById(R.id.iQuantity);
            holder.basketRemove = (ImageView) convertView
                    .findViewById(R.id.deleteItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final BasketItem basketItem = (BasketItem) getItem(position);
        holder.basketItemName.setText(basketItem.getItemName());
        holder.basketQuantity.setText(basketItem.getQuantity());

        if(basketItem.getItemName().trim().isEmpty()){
            holder.basketItemName.setVisibility(View.GONE);
        } else {
            holder.basketItemName.setText(basketItem.getItemName());
        }
        holder.basketRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder
                        .setTitle("Remove from Basket")
                        .setMessage("Are you sure you want to remove the item from Basket?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                remove(basketItem);
                                BasketItemDAO basketItemDAO;
                                basketItemDAO = new BasketItemDAO(getContext());
                                // Use AsyncTask to delete from database
                                basketItemDAO.removeBasketItem(basketItem);


                                /*if (context instanceof BasketItemListActivity) {

                                    TextView basketComplaintsCount = (TextView) ((BasketItemListActivity) context).findViewById(R.id.basketComplaintsCount);
                                    basketComplaintsCount.setText("Total Complaints in Basket : " + basketComplaints.size());

                                }*/


                                if (basketItems.size() == 0) {
                                    final Toast toast = Toast.makeText(getContext(), "No Items in Basket", Toast.LENGTH_LONG);
                                    toast.show();

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            toast.cancel();
                                            Intent myIntent = new Intent(getContext(), IngredientTypeActivity.class);
                                            getContext().startActivity(myIntent);
                                        }
                                    }, 500);

                                } else {
//                                    Toast.makeText(getContext(), "" + basketComplaints.size(), Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                /*Button posB = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negB = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                posB.setBackgroundResource(R.drawable.blue_dark_blue_highlight);
                posB.setTextColor(Color.WHITE);
                posB.setTransformationMethod(null);
                negB.setTransformationMethod(null);

                if (context instanceof BasketComplaintListActivity) {
                    TextView basketComplaintsCount = (TextView) ((BasketComplaintListActivity) context).findViewById(R.id.basketComplaintsCount);
                    basketComplaintsCount.setText("Total Complaints in Basket : " + String.valueOf(basketComplaints.size()));
                }*/

            }
        });

        /*holder.editBasketComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(context, EditDetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable("complaint", basketComplaint);
                myIntent.putExtras(mBundle);
                getContext().startActivity(myIntent);
            }
        });*/

        return convertView;
    }

    @Override
    public void add(BasketItem basketItem) {
        basketItems.add(basketItem);
        notifyDataSetChanged();
        super.add(basketItem);
    }

    @Override
    public void remove(BasketItem basketItem) {
        basketItems.remove(basketItem);
        notifyDataSetChanged();
        super.remove(basketItem);
    }

}