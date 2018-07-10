package com.example.sumitlakra.rentmanager.data.db;

import android.database.sqlite.SQLiteConstraintException;

import com.example.sumitlakra.rentmanager.data.db.model.RentHistory;
import com.example.sumitlakra.rentmanager.data.db.model.Room;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *  Interface to be implemented by AppDbHelper and contains methods exposed to the
 *  application components. This layer decouples any specific implementation of the DbHelper
 *  and hence makes AppDbHelper as plug and play unit.
 */
public interface DbHelper {

    List<Room> getRoomList();

    Long addRoom(Room room) throws SQLiteConstraintException;

    Long deleteRoom(String roomNo);

    List<Room> getRoomDetails(String roomNo, String month);

    Long saveComment(String comments, String roomNo, String month);

    String calculateRent(String meterReading, String roomReading, String roomNo, String month);

    int getLastMainMeterReading(String roomNo, String month);

    int getLastRoomReading(String roomNo, String month);

    Long payRent(String roomNo, String rent, String month);

    int getBalance(String roomNo, String month);

    boolean getRentPaidStatus(String roomNo, String month);

    List<RentHistory> getCompleteRentRecord();

    List<RentHistory> getRoomRentRecord(String roomNo);

    Long generateNewMonthBill(String roomNo, String month, String prevMonth);

    boolean checkRoomExists(String roomNo, String month);

    String getElectricityBill(String roomNo, String month);

    String getWaterBill(String roomNo, String month);

    Long saveNewDetails(Room room);

    Room getEditRoomDetails(String roomNo);

    List<Room> getAllDetails(String roomNo, String month);
}
