package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.showorld.housestar.model.BasketItem;

import java.util.ArrayList;

public class BasketItemDAO extends BasketItemDBDAO {

    private static final String WHERE_ID_EQUALS = BasketItemsHelper.KEY_ID
            + " =?";

    private Context mContext;
    public BasketItemDAO(Context context) {
        super(context);
        this.mContext = context;
    }

    public long saveBasketItem(BasketItem basketItem) {
        ContentValues values = new ContentValues();

        values.put(BasketItemsHelper.KEY_ITEM_NAME, basketItem.getItemName());
        values.put(BasketItemsHelper.KEY_ITEM_QUANTITY, basketItem.getQuantity());

        return database.insert(BasketItemsHelper.TABLE_NAME, null, values);
    }

    public long updateBasketItem(BasketItem basketItem) {
        ContentValues values = new ContentValues();

        values.put(BasketItemsHelper.KEY_ITEM_NAME, basketItem.getItemName());
        values.put(BasketItemsHelper.KEY_ITEM_QUANTITY, basketItem.getQuantity());

        long result = database.update(BasketItemsHelper.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(basketItem.getId()) });
        Log.d("Update Result:", "=" + result);
        //Toast.makeText(mContext, "" + result, Toast.LENGTH_LONG).show();

        return result;
    }

    public int removeBasketItem(BasketItem basketItem) {
        return database.delete(BasketItemsHelper.TABLE_NAME, WHERE_ID_EQUALS,
                new String[] { basketItem.getId() + "" });
    }

    //USING query() method
    public ArrayList<BasketItem> getAllBasketItems() {
        ArrayList<BasketItem> basketItems = new ArrayList<BasketItem>();

        Cursor cursor = database.query(BasketItemsHelper.TABLE_NAME,
                new String[] { BasketItemsHelper.KEY_ID,
                        BasketItemsHelper.KEY_ITEM_NAME,
                        BasketItemsHelper.KEY_ITEM_QUANTITY }, null, null, null,
                null, null, null);

        while (cursor.moveToNext()) {
            BasketItem basketItem = new BasketItem ();
            basketItem.setId(cursor.getInt(0));
            basketItem.setItemName(cursor.getString(1));
            basketItem.setQuantity(cursor.getString(2));
            basketItems.add(basketItem);
        }
        return basketItems;
    }

    //Retrieves a single employee record with the given id
    public BasketItem getBasketItem(long id) {
        BasketItem basketItem = null;

        String sql = "SELECT * FROM " + BasketItemsHelper.TABLE_NAME
                + " WHERE " + BasketItemsHelper.KEY_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            basketItem = new BasketItem ();
            basketItem.setId(cursor.getInt(0));
            basketItem.setId(cursor.getInt(0));
            basketItem.setItemName(cursor.getString(1));
            basketItem.setQuantity(cursor.getString(2));
        }
        return basketItem;
    }

    //Retrieves a single employee record with the given id
    public BasketItem getBasketItemObjectByItemName(String itemName) {
        BasketItem basketItem = null;

        String sql = "SELECT * FROM " + BasketItemsHelper.TABLE_NAME
                + " WHERE " + BasketItemsHelper.KEY_ITEM_NAME + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { itemName + "" });

        if (cursor.moveToNext()) {
            basketItem = new BasketItem ();
            basketItem.setId(cursor.getInt(0));
            basketItem.setId(cursor.getInt(0));
            basketItem.setItemName(cursor.getString(1));
            basketItem.setQuantity(cursor.getString(2));
        }
        return basketItem;
    }

    //Retrieves a single employee record with the given id
    public boolean getBasketItemByItemName(String itemName) {

        String sql = "SELECT * FROM " + BasketItemsHelper.TABLE_NAME
                + " WHERE " + BasketItemsHelper.KEY_ITEM_NAME + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { itemName + "" });

        if (cursor != null && cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }
    }

}
