package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProcedureListHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MyProcedureList.db";

    public static final String TABLE_NAME = "procedure";

    public static final String KEY_ID = "id";
    public static final String KEY_ITEM_LIST = "itemList";
    public static final String KEY_SELECT_PROCEDURE = "SelectProcedure";
    public static final String KEY_COMMENT = "Comment";
    public static final String KEY_TIME_HOURS = "TimeHours";
    public static final String KEY_TIME_MINUTES = "TimeMinutes";

    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM_LIST + " TEXT,"
            + KEY_SELECT_PROCEDURE + " TEXT," + KEY_COMMENT + " TEXT,"
            + KEY_TIME_HOURS + " TEXT," + KEY_TIME_MINUTES + " TEXT" + ")";

    private static ProcedureListHelper instance;

    public static synchronized ProcedureListHelper getHelper(Context context) {
        if (instance == null)
            instance = new ProcedureListHelper(context);
        return instance;
    }

    public ProcedureListHelper(Context context) {
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
