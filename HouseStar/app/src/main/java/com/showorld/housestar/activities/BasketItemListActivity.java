package com.showorld.housestar.activities;

import android.app.Activity;
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
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.adapters.BasketItemListAdapter;
import com.showorld.housestar.adapters.SpinnerAdapter;
import com.showorld.housestar.adapters.SpinnerAdapterYo;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.model.BasketItem;

import java.util.ArrayList;

public class BasketItemListActivity extends Activity{

    AbsListView absListView;
    public String IngredientName = "";

    public String measuringUnit = "";
    public String amount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cooking_cart);

        Button cookingProcedure = (Button) findViewById(R.id.ButtonStartCooking);
        cookingProcedure.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(BasketItemListActivity.this, Procedure.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        final BasketItemDAO basketItemDAO = new BasketItemDAO(getApplicationContext());
        final ArrayList<BasketItem> basketItems;
        basketItems = basketItemDAO.getAllBasketItems();

        absListView = (AbsListView) findViewById(R.id.ListViewCatalog);
        absListView.setAdapter(new BasketItemListAdapter(this, basketItems));

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                BasketItem bItem = basketItems.get(position);
                Log.d("output", bItem.getItemName());
                OpenPopUp(view, bItem);

            }
        });

    }

    public void OpenPopUp(View arg0, final BasketItem bItem) {

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.popup_menu, null);

        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnDone = (Button)popupView.findViewById(R.id.done);
        Button btnCancel = (Button)popupView.findViewById(R.id.cancel);
        final EditText number = (EditText)popupView.findViewById(R.id.number);
        LinearLayout screen = (LinearLayout)popupView.findViewById(R.id.popupscreen);
        final Spinner popupSpinner = (Spinner)popupView.findViewById(R.id.popupspinner);

        btnDone.setText("Change");
        btnCancel.setText("Cancel");

        Resources res = getResources();
        String[] name = res.getStringArray(R.array.MeasurementUnit);
        TypedArray images = res.obtainTypedArray(R.array.MeasurmentImages);

        popupSpinner.setAdapter(new SpinnerAdapterYo(BasketItemListActivity.this, name, images));

        popupWindow.setFocusable(true);

        final BasketItemDAO bDao = new BasketItemDAO(getApplicationContext());

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

                    bItem.setQuantity(quantity);
                    bDao.updateBasketItem(bItem);
                    absListView.invalidateViews();

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

        //popupWindow.showAsDropDown(popupSpinner, 20, -30);
        popupWindow.showAtLocation(absListView, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BasketItemListActivity.this, IngredientTypeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}
