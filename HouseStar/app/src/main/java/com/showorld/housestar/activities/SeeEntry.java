package com.showorld.housestar.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.helpers.ProcedureListDAO;
import com.showorld.housestar.model.BasketItem;
import com.showorld.housestar.model.ProcedureList;

import java.util.ArrayList;

/**
 * Created by DELL on 22-07-2015.
 */
public class SeeEntry extends Activity {

    private ImageView imgPreview;
    protected TextView listIngredients;
    protected TextView listProcedure;
    protected Button submit;

    private String ImagePath = null;

    String name, quantity, index;

    String item_arr, procedure_arr, comment_arr, hour_arr, minute_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        // Receiving the data from previous activity
        Intent i = getIntent();

        ImagePath = i.getStringExtra("ImagePath");

        imgPreview = (ImageView) findViewById(R.id.imageItem);



        if (ImagePath == null) { //ImagePath.equals("") ||
            // Displaying the image or video on the screen
            Toast.makeText(getApplicationContext(),
                    "Sorry, no Image selected! So Default Image", Toast.LENGTH_SHORT).show();
        } else {
            previewMedia();
        }

        listIngredients = (TextView) findViewById(R.id.listIng);
        listProcedure = (TextView) findViewById(R.id.listPro);

        //Toast.makeText(getApplicationContext(),"Image Path:" + ImagePath, Toast.LENGTH_SHORT).show();

        //Log.d("Image Path:", ImagePath);

        final ProcedureListDAO procedureListDAO = new ProcedureListDAO(getApplicationContext());
        final ArrayList<ProcedureList> procedureList;
        procedureList = procedureListDAO.getAllProcedureList();

        final BasketItemDAO basketItemDAO = new BasketItemDAO(getApplicationContext());
        final ArrayList<BasketItem> basketItems;
        basketItems = basketItemDAO.getAllBasketItems();

        String items = "";
        for (int x = 0; x < basketItems.size(); x = x + 1)
        {
            name = basketItems.get(x).getItemName().toString();
            quantity = basketItems.get(x).getQuantity().toString();
            index = Integer.toString(x + 1);

            items = items + index + ". " + name + "  -->  " +
                    quantity + "\n\n";
        }

        listIngredients.setText(items);

        String description = "";
        for (int x = 0; x < procedureList.size(); x = x + 1)
        {
            item_arr = procedureList.get(x).getItemList().toString();
            procedure_arr = procedureList.get(x).getSelectProcedure().toString();
            comment_arr = procedureList.get(x).getComment().toString();
            hour_arr = procedureList.get(x).getTimeHours().toString();
            minute_arr = procedureList.get(x).getTimeMinutes().toString();
            index = Integer.toString(x + 1);

            description = description + index + ". " + "<i>" + procedure_arr + "</i> " +
                    "<b>" + item_arr + "</b> " + comment_arr + " for " + hour_arr +
                    " hour(s) and/or " + minute_arr + " minute(s) " + "<br><br>";
        }

        listProcedure.setText(Html.fromHtml(description));
        //listProcedure.setText(description);
    }

    private void previewMedia() {

            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(ImagePath, options);

            imgPreview.setImageBitmap(bitmap);

    }

}
