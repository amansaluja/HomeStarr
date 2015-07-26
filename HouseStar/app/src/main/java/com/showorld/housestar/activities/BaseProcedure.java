package com.showorld.housestar.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.showorld.housestar.R;
import com.showorld.housestar.adapters.ProcedureGridAdapter;
import com.showorld.housestar.adapters.SpinnerAdapter;
import com.showorld.housestar.adapters.SpinnerAdapterYo;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.helpers.ProcedureListDAO;
import com.showorld.housestar.model.BasketItem;
import com.showorld.housestar.model.ProcedureList;

import java.util.ArrayList;

/**
 * Created by DELL on 18-07-2015.
 */

public class BaseProcedure extends Activity implements View.OnClickListener {

    AbsListView absListView;
    ArrayList<String> mybasket = new ArrayList<String>();
    public String SelectProcedure = "";
    public String Comment = "";
    public String TimeHours = "";
    public String TimeMinutes = "";
    public String itemList = "";
    public EditText Hours, Minutes;
    public Button done, cancel;

    public Spinner Spinner, Comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_procedure);

        final BasketItemDAO basketItemDAO = new BasketItemDAO(getApplicationContext());
        final ArrayList<BasketItem> gridItems;
        gridItems = basketItemDAO.getAllBasketItems();

        absListView = (AbsListView) findViewById(R.id.gview);
        absListView.setAdapter(new ProcedureGridAdapter(this, gridItems));

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        Hours = (EditText) findViewById(R.id.hour);
        Minutes = (EditText) findViewById(R.id.minute);

        Spinner = (Spinner) findViewById(R.id.proclist);
        Comments = (Spinner) findViewById(R.id.comment);

        final Boolean[] colorSet = new Boolean[absListView.getAdapter().getCount()];
        final Boolean[] selectionSet = new Boolean[absListView.getAdapter().getCount()];

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                BasketItem bItem = gridItems.get(position);

                if (selectionSet[position] == null || !selectionSet[position]) {

                    mybasket.add(bItem.getItemName());
                    selectionSet[position] = true;
                } else {
                    mybasket.remove(bItem.getItemName());
                    selectionSet[position] = false;
                }

                if (colorSet[position] == null || !colorSet[position]) {
                    view.setBackgroundColor(getResources().getColor(R.color.orange));
                    colorSet[position] = true;
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                    colorSet[position] = false;
                }

                itemList = String.valueOf(mybasket);

            }
        });

        Resources res = getResources();
        String[] name = res.getStringArray(R.array.procNames);
        TypedArray images = res.obtainTypedArray(R.array.procImages);
        String[] commentType = res.getStringArray(R.array.comment);

        Spinner.setAdapter(new SpinnerAdapterYo(BaseProcedure.this, name, images));
        Comments.setAdapter(new SpinnerAdapter(BaseProcedure.this, commentType));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.done:

                SelectProcedure = Spinner.getSelectedItem().toString();
                Comment = Comments.getSelectedItem().toString();
                TimeHours = Hours.getText().toString();
                TimeMinutes = Minutes.getText().toString();

                Log.d("Insert: ", "Inserting ..");

                ProcedureList p = new ProcedureList();
                p.setItemList(itemList);
                p.setSelectProcedure(SelectProcedure);
                p.setComment(Comment);
                p.setTimeHours(TimeHours);
                p.setTimeMinutes(TimeMinutes);
                ProcedureListDAO pDao = new ProcedureListDAO(getApplicationContext());
                pDao.saveProcedureList(p);

                invalidateOptionsMenu();

                Intent i = new Intent(this, Procedure.class);
                startActivity(i);
                break;

            case R.id.cancel:
                Intent j = new Intent(this, Procedure.class);
                startActivity(j);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BaseProcedure.this, Procedure.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}