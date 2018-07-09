package com.example.sumitlakra.rentmanager.data.db;

import android.content.Context;

import com.example.sumitlakra.rentmanager.data.db.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Custom OpenHelper class to the DaoMaster
 *  should extend DaoMaster.OpenHelper
 */
public class DbOpenHelper extends DaoMaster.OpenHelper {
    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
