package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.showorld.housestar.model.ProcedureList;

import java.util.ArrayList;

public class ProcedureListDAO extends ProcedureListDBDAO {

    private static final String WHERE_ID_EQUALS = ProcedureListHelper.KEY_ID
            + " =?";

    private Context mContext;
    public ProcedureListDAO(Context context) {
        super(context);
        this.mContext = context;
    }

    public long saveProcedureList(ProcedureList procedureList) {
        ContentValues values = new ContentValues();

        values.put(ProcedureListHelper.KEY_ITEM_LIST, procedureList.getItemList());
        values.put(ProcedureListHelper.KEY_SELECT_PROCEDURE, procedureList.getSelectProcedure());
        values.put(ProcedureListHelper.KEY_COMMENT, procedureList.getComment());
        values.put(ProcedureListHelper.KEY_TIME_HOURS, procedureList.getTimeHours());
        values.put(ProcedureListHelper.KEY_TIME_MINUTES, procedureList.getTimeMinutes());

        return database.insert(ProcedureListHelper.TABLE_NAME, null, values);
    }

    public long updateProcedureList(ProcedureList procedureList) {
        ContentValues values = new ContentValues();

        values.put(ProcedureListHelper.KEY_ITEM_LIST, procedureList.getItemList());
        values.put(ProcedureListHelper.KEY_SELECT_PROCEDURE, procedureList.getSelectProcedure());
        values.put(ProcedureListHelper.KEY_COMMENT, procedureList.getComment());
        values.put(ProcedureListHelper.KEY_TIME_HOURS, procedureList.getTimeHours());
        values.put(ProcedureListHelper.KEY_TIME_MINUTES, procedureList.getTimeMinutes());

        long result = database.update(ProcedureListHelper.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(procedureList.getId()) });
        Log.d("Update Result:", "=" + result);

        return result;
    }

    public int removeProcedureList(ProcedureList procedureList) {
        return database.delete(ProcedureListHelper.TABLE_NAME, WHERE_ID_EQUALS,
                new String[] { procedureList.getId() + "" });
    }

    //USING query() method
    public ArrayList<ProcedureList> getAllProcedureList() {
        ArrayList<ProcedureList> procedureLists = new ArrayList<ProcedureList>();

        Cursor cursor = database.query(ProcedureListHelper.TABLE_NAME,
                new String[] { ProcedureListHelper.KEY_ID,
                        ProcedureListHelper.KEY_ITEM_LIST,
                        ProcedureListHelper.KEY_SELECT_PROCEDURE,
                        ProcedureListHelper.KEY_COMMENT,
                        ProcedureListHelper.KEY_TIME_HOURS,
                        ProcedureListHelper.KEY_TIME_MINUTES}, null, null, null,
                null, null, null);

        while (cursor.moveToNext()) {
            ProcedureList procedureList = new ProcedureList ();
            procedureList.setId(cursor.getInt(0));
            procedureList.setItemList(cursor.getString(1));
            procedureList.setSelectProcedure(cursor.getString(2));
            procedureList.setComment(cursor.getString(3));
            procedureList.setTimeHours(cursor.getString(4));
            procedureList.setTimeMinutes(cursor.getString(5));
            procedureLists.add(procedureList);
        }
        return procedureLists;
    }

    //Retrieves a single employee record with the given id
    public ProcedureList getProcedureList(long id) {
        ProcedureList procedureList = null;

        String sql = "SELECT * FROM " + ProcedureListHelper.TABLE_NAME
                + " WHERE " + ProcedureListHelper.KEY_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            procedureList = new ProcedureList ();
            procedureList.setId(cursor.getInt(0));
            procedureList.setId(cursor.getInt(0));
            procedureList.setItemList(cursor.getString(1));
            procedureList.setSelectProcedure(cursor.getString(2));
            procedureList.setComment(cursor.getString(3));
            procedureList.setTimeHours(cursor.getString(4));
            procedureList.setTimeMinutes(cursor.getString(5));
        }
        return procedureList;
    }

    //Retrieves a single employee record with the given id
    public ProcedureList getProcedureListObjectByItemList(String itemList) {
        ProcedureList procedureList = null;

        String sql = "SELECT * FROM " + ProcedureListHelper.TABLE_NAME
                + " WHERE " + ProcedureListHelper.KEY_ITEM_LIST + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { itemList + "" });

        if (cursor.moveToNext()) {
            procedureList = new ProcedureList ();
            procedureList.setId(cursor.getInt(0));
            procedureList.setId(cursor.getInt(0));
            procedureList.setItemList(cursor.getString(1));
            procedureList.setSelectProcedure(cursor.getString(2));
            procedureList.setComment(cursor.getString(3));
            procedureList.setTimeHours(cursor.getString(4));
            procedureList.setTimeMinutes(cursor.getString(5));
        }
        return procedureList;
    }

    //Retrieves a single employee record with the given id
    public boolean getProcedureListByItemList(String itemList) {

        String sql = "SELECT * FROM " + ProcedureListHelper.TABLE_NAME
                + " WHERE " + ProcedureListHelper.KEY_ITEM_LIST + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { itemList + "" });

        if (cursor != null && cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }
    }

}
