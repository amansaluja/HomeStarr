package com.showorld.housestar.helpers;

/**
 * Created by Aman on 14-07-2015.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProcedureListDBDAO {

    protected SQLiteDatabase database;
    private ProcedureListHelper dbHelper;
    private Context mContext;

    public ProcedureListDBDAO(Context context) {
        this.mContext = context;
        dbHelper = ProcedureListHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = ProcedureListHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}
