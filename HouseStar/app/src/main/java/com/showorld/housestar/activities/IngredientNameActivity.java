package com.showorld.housestar.activities;

/**
 * Created by Aman on 06-07-2015.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.adapters.ListViewAdapter;
import com.showorld.housestar.adapters.SpinnerAdapterYo;
import com.showorld.housestar.base.BaseActivity;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.model.BasketItem;

public class IngredientNameActivity extends BaseActivity {


    AbsListView absListView;
    public String IngredientName = "";

    public String measuringUnit = "";
    public String amount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final String[] listItems = intent.getStringArrayExtra("Options");
        int[] listImages = intent.getIntArrayExtra("OptionImages");

        setContentView(R.layout.view_list);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Please select from items below");

        absListView = (AbsListView) findViewById(R.id.listView);
        absListView.setAdapter(new ListViewAdapter(this, listItems, listImages));

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                IngredientName = listItems[position];
                OpenPopUp(view);

            }
        });
    }

    public void OpenPopUp(View arg0) {
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.popup_menu, null);

        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnDone = (Button)popupView.findViewById(R.id.done);
        Button btnCancel = (Button)popupView.findViewById(R.id.cancel);
        final EditText number = (EditText)popupView.findViewById(R.id.number);
        LinearLayout screen = (LinearLayout)popupView.findViewById(R.id.popupscreen);
        final Spinner popupSpinner = (Spinner)popupView.findViewById(R.id.popupspinner);

        Resources res = getResources();
        String[] name = res.getStringArray(R.array.MeasurementUnit);
        TypedArray images = res.obtainTypedArray(R.array.MeasurmentImages);

        popupSpinner.setAdapter(new SpinnerAdapterYo(IngredientNameActivity.this, name, images));

        popupWindow.setFocusable(true);

        btnDone.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                measuringUnit = popupSpinner.getSelectedItem().toString();
                amount = number.getText().toString();

                if (amount.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter the quantity!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    popupWindow.dismiss();

                    String quantity = amount + " " + measuringUnit;

                    Log.d("Insert: ", "Inserting ..");

                    BasketItem b = new BasketItem();
                    b.setItemName(IngredientName);
                    b.setQuantity(quantity);
                    BasketItemDAO bDao = new BasketItemDAO(getApplicationContext());
                    bDao.saveBasketItem(b);

                    invalidateOptionsMenu();

                }
            }
        });

        btnCancel.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        screen.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(absListView, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(IngredientNameActivity.this, IngredientTypeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}

