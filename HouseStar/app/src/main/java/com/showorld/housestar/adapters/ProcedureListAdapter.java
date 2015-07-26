package com.showorld.housestar.adapters;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.activities.IngredientTypeActivity;
import com.showorld.housestar.activities.Procedure;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.helpers.ProcedureListDAO;
import com.showorld.housestar.model.BasketItem;
import com.showorld.housestar.model.ProcedureList;

import java.util.List;

public class ProcedureListAdapter extends ArrayAdapter<ProcedureList> {

    private Context context;
    List<ProcedureList> procedureLists;
    private ProcedureListDAO procedureListDAO;
    ProcedureList procedureList;
    private ProgressDialog progressDialog;
    Boolean removedFromProcedure = false;

    public ProcedureListAdapter(Context context, List<ProcedureList> procedureLists) {
        super(context, R.layout.fragment_item_list, procedureLists);
        this.context = context;
        this.procedureLists = procedureLists;

    }

    private class ViewHolder {
        TextView basketItemList;
        TextView basketItemNone;
        ImageView basketRemove;
    }

    @Override
    public int getCount() {
        return procedureLists.size();
    }

    @Override
    public ProcedureList getItem(int position) {
        return procedureLists.get(position);
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
            procedureListDAO = new ProcedureListDAO(getContext());
            holder.basketItemList = (TextView) convertView
                    .findViewById(R.id.iName);
            holder.basketItemNone = (TextView) convertView
                    .findViewById(R.id.iQuantity);
            holder.basketRemove = (ImageView) convertView
                    .findViewById(R.id.deleteItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ProcedureList procedureList = (ProcedureList) getItem(position);
        holder.basketItemList.setText(procedureList.getSelectProcedure());
        holder.basketItemNone.setVisibility(View.GONE);

        if(procedureList.getItemList().trim().isEmpty()){
            holder.basketItemList.setText(procedureList.getSelectProcedure()); /// Some ISSUE here
        } else {
            holder.basketItemList.setText(procedureList.getSelectProcedure());
        }

        holder.basketRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder
                        .setTitle("Remove from Procedure")
                        .setMessage("Are you sure you want to remove this step?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                remove(procedureList);
                                ProcedureListDAO procedureListDAO;
                                procedureListDAO = new ProcedureListDAO(getContext());
                                // Use AsyncTask to delete from database
                                procedureListDAO.removeProcedureList(procedureList);

                                if (procedureLists.size() == 0) {
                                    final Toast toast = Toast.makeText(getContext(), "No Step in Procedure List", Toast.LENGTH_LONG);
                                    toast.show();

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            toast.cancel();
                                            Intent myIntent = new Intent(getContext(), Procedure.class);
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

            }
        });

        return convertView;
    }

}