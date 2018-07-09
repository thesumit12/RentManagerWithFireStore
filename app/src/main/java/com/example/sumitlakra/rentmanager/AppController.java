package com.example.sumitlakra.rentmanager;

import android.app.Application;

import com.example.sumitlakra.rentmanager.data.db.DbOpenHelper;
import com.example.sumitlakra.rentmanager.data.db.model.DaoMaster;
import com.example.sumitlakra.rentmanager.data.db.model.DaoSession;

public class AppController extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mDaoSession = new DaoMaster(
                new DbOpenHelper(this,"rooms.db").getWritableDb()
        ).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
