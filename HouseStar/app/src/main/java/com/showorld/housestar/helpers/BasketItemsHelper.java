package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BasketItemsHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MyBasketItemList.db";

    public static final String TABLE_NAME = "items";

    public static final String KEY_ID = "id";
    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_ITEM_QUANTITY = "quantity";

    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM_NAME + " TEXT,"
            + KEY_ITEM_QUANTITY + " TEXT" + ")";

    private static BasketItemsHelper instance;

    public static synchronized BasketItemsHelper getHelper(Context context) {
        if (instance == null)
            instance = new BasketItemsHelper(context);
        return instance;
    }

    public BasketItemsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
