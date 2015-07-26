package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BasketItemDBDAO {

    protected SQLiteDatabase database;
    private BasketItemsHelper dbHelper;
    private Context mContext;

    public BasketItemDBDAO(Context context) {
        this.mContext = context;
        dbHelper = BasketItemsHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = BasketItemsHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}
