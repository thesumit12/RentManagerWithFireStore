package com.example.sumitlakra.rentmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.sumitlakra.rentmanager.data.db.DbHelper;
import com.example.sumitlakra.rentmanager.data.db.model.RentHistory;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.data.network.ApiHelper;

import java.sql.SQLException;
import java.util.List;

/**
 *  It is the one point of contact for any data related operation in the application.
 *  DbHelper, PreferenceHelper, and ApiHelper only work for DataManager. It delegates
 *  all the operations specific to any Helper.
 */
public class AppDataManger implements DataManager {

    private static final String TAG = "AppDataManager";
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;

    public AppDataManger(Context context, DbHelper dbHelper, ApiHelper apiHelper){
        this.mContext = context;
        this.mDbHelper = dbHelper;
        this.mApiHelper = apiHelper;
    }

    @Override
    public List<Room> getRoomList() {
        return mDbHelper.getRoomList();
    }

    @Override
    public Long addRoom(Room room) throws SQLiteConstraintException {
        return mDbHelper.addRoom(room);
    }

    @Override
    public Long deleteRoom(String roomNo) {
        return mDbHelper.deleteRoom(roomNo);
    }

    @Override
    public List<Room> getRoomDetails(String roomNo, String month) {
        return mDbHelper.getRoomDetails(roomNo, month);
    }

    @Override
    public Long saveComment(String comments, String roomNo, String month) {
        return mDbHelper.saveComment(comments, roomNo, month);
    }

    @Override
    public String calculateRent(String meterReading, String roomReading, String roomNo, String month) {
        return mDbHelper.calculateRent(meterReading,roomReading,roomNo, month);
    }

    @Override
    public int getLastMainMeterReading(String roomNo, String month) {
        return mDbHelper.getLastMainMeterReading(roomNo, month);
    }

    @Override
    public int getLastRoomReading(String roomNo, String month) {
        return mDbHelper.getLastRoomReading(roomNo, month);
    }

    @Override
    public Long payRent(String roomNo, String rent, String month) {
        return mDbHelper.payRent(roomNo, rent, month);
    }

    @Override
    public int getBalance(String roomNo, String month) {
        return mDbHelper.getBalance(roomNo,month);
    }

    @Override
    public boolean getRentPaidStatus(String roomNo, String month) {
        return mDbHelper.getRentPaidStatus(roomNo, month);
    }

    @Override
    public List<RentHistory> getCompleteRentRecord() {
        return mDbHelper.getCompleteRentRecord();
    }

    @Override
    public List<RentHistory> getRoomRentRecord(String roomNo) {
        return mDbHelper.getRoomRentRecord(roomNo);
    }

    @Override
    public Long generateNewMonthBill(String roomNo, String month, String prevMonth) {
        return mDbHelper.generateNewMonthBill(roomNo, month, prevMonth);
    }

    @Override
    public boolean checkRoomExists(String roomNo, String month) {
        return mDbHelper.checkRoomExists(roomNo, month);
    }

    @Override
    public String getElectricityBill(String roomNo, String month) {
        return mDbHelper.getElectricityBill(roomNo, month);
    }

    @Override
    public String getWaterBill(String roomNo, String month) {
        return mDbHelper.getWaterBill(roomNo, month);
    }

    @Override
    public Long saveNewDetails(Room room) {
        return mDbHelper.saveNewDetails(room);
    }
}
