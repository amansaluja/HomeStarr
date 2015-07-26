package com.showorld.housestar.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.activities.BasketItemListActivity;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.model.BasketItem;

import java.util.ArrayList;

/**
 * Created by Aman on 10-07-2015.
 */

public class UpdateItemsCount {

    ArrayList<BasketItem> basketItems;
    BasketItemDAO basketItemDAO;
    int basketItemSize = 0;
    int hot_number = 0;
    TextView count;
    ImageView cart;

    private Context mContext;
    Menu currentMenu;

    public UpdateItemsCount(Context context, Menu menu){
        super();
        this.mContext = context;
        this.currentMenu = menu;
        new GetBasketItems().execute();
    }

    public class GetBasketItems extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                basketItemDAO = new BasketItemDAO(mContext);
                basketItems = basketItemDAO.getAllBasketItems();
                basketItemSize = basketItems.size();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateHotCount(basketItemSize);
        }
    }

    public void updateHotCount(final int new_hot_number) {

        MenuItem item = currentMenu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.cart_popup);
        View view = MenuItemCompat.getActionView(item);
        count = (TextView) view.findViewById(R.id.numberItems);
        cart = (ImageView) view.findViewById(R.id.cart);

        hot_number = new_hot_number;
        if (count == null) return;
        if (new_hot_number == 0) {
            count.setVisibility(View.INVISIBLE);
        } else {
            count.setVisibility(View.VISIBLE);
            count.setText(Integer.toString(new_hot_number));
        }

        new MyMenuItemStuffListener(view, new_hot_number + " items in Basket")/* {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BasketItemListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        }*/;

    }

    public class MyMenuItemStuffListener implements View.OnClickListener, View.OnLongClickListener {
        private String hint;
        private View view;

        public MyMenuItemStuffListener(View view, String hint) {
            this.view = view;
            this.hint = hint;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i = new Intent(mContext, BasketItemListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);

        }

        @Override
        public boolean onLongClick(View v) {
            final int[] screenPos = new int[2];
            final Rect displayFrame = new Rect();
            view.getLocationOnScreen(screenPos);
            view.getWindowVisibleDisplayFrame(displayFrame);
            final Context context = view.getContext();
            final int width = view.getWidth();
            final int height = view.getHeight();
            final int midy = screenPos[1] + height / 2;
            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            Toast cheatSheet = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
            if (midy < displayFrame.height()) {
                cheatSheet.setGravity(Gravity.TOP | Gravity.RIGHT,
                        screenWidth - screenPos[0] - width / 2, height);
            } else {
                cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
            }
            cheatSheet.show();
            return true;
        }
    }

}