package com.example.sumitlakra.rentmanager.data.db;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.sumitlakra.rentmanager.data.db.model.DaoSession;
import com.example.sumitlakra.rentmanager.data.db.model.RentHistory;
import com.example.sumitlakra.rentmanager.data.db.model.RentHistoryDao;
import com.example.sumitlakra.rentmanager.data.db.model.RentTransaction;
import com.example.sumitlakra.rentmanager.data.db.model.RentTransactionDao;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.data.db.model.RoomDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Database management and all the data handling related to a database is done in this
 * part of the application
 */
public class AppDbHelper implements DbHelper {

    private static final int ERRORCODE = 9999;

    private final DaoSession mDaoSession;

    public AppDbHelper(DaoSession session){
        this.mDaoSession = session;
    }

    @Override
    public List<Room> getRoomList() {
        return mDaoSession.getRoomDao().queryBuilder().orderAsc(RoomDao.Properties.RoomNumber).list();
    }

    @Override
    public Long addRoom(Room room) throws SQLiteConstraintException {
        RentTransaction rentTransaction = new RentTransaction();
        rentTransaction.setName(room.getName());
        rentTransaction.setRoomNo(room.getRoomNumber());
        rentTransaction.setMonth(room.getMonth());

        mDaoSession.getRentTransactionDao().insert(rentTransaction);
        return mDaoSession.getRoomDao().insert(room);
    }

    @Override
    public Long saveNewDetails(Room room) {
        List<Room> roomList = mDaoSession.queryBuilder(Room.class)
                .where(RoomDao.Properties.RoomNumber.eq(room.getRoomNumber())).list();
        Room newRoom = roomList.get(0);
        room.setImageId(newRoom.getImageId());
        room.setTotalRent(newRoom.getTotalRent());
        room.setComments(newRoom.getComments());
        room.setMonth(newRoom.getMonth());
        room.setStartMonth(newRoom.getStartMonth());
        room.setTempRoomReading(newRoom.getTempRoomReading());
        room.setTempMainMeterReading(newRoom.getTempMainMeterReading());
        return mDaoSession.getRoomDao().insertOrReplace(room);
    }

    @Override
    public Room getEditRoomDetails(String roomNo) {
        List<Room> roomList = mDaoSession.queryBuilder(Room.class)
                .where(RoomDao.Properties.RoomNumber.eq(roomNo)).list();
        return roomList.get(0);
    }

    @Override
    public List<Room> getAllDetails(String roomNo, String month) {
        return mDaoSession.queryBuilder(Room.class).where(RoomDao.Properties.RoomNumber.eq(roomNo),
                RoomDao.Properties.Month.eq(month)).list();
    }

    @Override
    public Long deleteRoom(String roomNo) {
        List<Room> oldList = mDaoSession.loadAll(Room.class);
        mDaoSession.queryBuilder(Room.class).where(RoomDao.Properties.RoomNumber.eq(roomNo)).
                buildDelete().executeDeleteWithoutDetachingEntities();
        List<Room> newList = mDaoSession.loadAll(Room.class);
        return (long) (oldList.size() - newList.size());
    }

    @Override
    public List<Room> getRoomDetails(String roomNo, String month) {
        Room roomDetails = new Room();
        List<Room> detailList = new ArrayList<>();
        List<Room> roomList = mDaoSession.queryBuilder(Room.class).
                where(RoomDao.Properties.RoomNumber.eq(roomNo)).list();
        List<RentTransaction> rentTransactionList = mDaoSession.queryBuilder(RentTransaction.class)
                .where(RentTransactionDao.Properties.RoomNo.eq(roomNo),
                        RentTransactionDao.Properties.Month.eq(month)).list();
        if (roomList.size() > 0){
            Room room = roomList.get(0);
            roomDetails.setMonth(room.getStartMonth());
            roomDetails.setName(room.getName());
            roomDetails.setTotalMembers(room.getTotalMembers());
            roomDetails.setRentDue(room.getRentDue());
            roomDetails.setStartMonth(room.getStartMonth());
        }
        if (rentTransactionList.size() > 0){
            RentTransaction rentTransaction = rentTransactionList.get(0);
            roomDetails.setComments(rentTransaction.getComments());
        }
        detailList.add(roomDetails);
        return detailList;
    }

    @Override
    public Long saveComment(String comments, String roomNo, String month) {
        List<Room> mList = mDaoSession.queryBuilder(Room.class).where(RoomDao.Properties.RoomNumber.eq(roomNo),
                RoomDao.Properties.Month.eq(month)).list();
        List<RentTransaction> rentTransactionList = mDaoSession.queryBuilder(RentTransaction.class)
                .where(RentTransactionDao.Properties.RoomNo.eq(roomNo),
                RentTransactionDao.Properties.Month.eq(month)).list();
        if (mList.size() > 0 && rentTransactionList.size() > 0 ){
            Room room = mList.get(0);
            room.setComments(comments);
            RentTransaction rentTransaction = rentTransactionList.get(0);
            rentTransaction.setComments(comments);
            mDaoSession.insertOrReplace(rentTransaction);
            return mDaoSession.insertOrReplace(room);
        }else
            return (long) ERRORCODE;
    }

    @Override
    public String calculateRent(String meterReading, String roomReading, String roomNo, String month) {
        int totalMembers = 0;
        Room mRoom = null;
        List<Room> mList = mDaoSession.loadAll(Room.class);
        for (int i=0; i<mList.size(); i++){
            totalMembers += mList.get(i).getAdults();
            if (mList.get(i).getRoomNumber().equals(roomNo) && mList.get(i).getMonth().equals(month)){
                mRoom = mList.get(i);
            }
        }
        if (mRoom != null){
            List<RentTransaction> transactionList = mDaoSession.queryBuilder(RentTransaction.class).
                    where(RentTransactionDao.Properties.RoomNo.eq(mRoom.getRoomNumber()),
                            RentTransactionDao.Properties.Month.eq(month)).list();
            RentTransaction mTransaction = transactionList.get(0);
            int balance =0;
            if (mTransaction.getBalance() != 1)
                balance = mTransaction.getBalance();

            int electricityBill = calculateElectricityBill(mRoom, roomReading);
            int waterBill = calculateWaterBill(mRoom, meterReading, totalMembers);

            float rent = calculateBaseRent(mRoom) + electricityBill + waterBill + balance;

            int totalRent = Math.round(rent);
            mRoom.setTotalRent(totalRent);
            mRoom.setTempMainMeterReading(Integer.valueOf(meterReading));
            mRoom.setTempRoomReading(Integer.valueOf(roomReading));
            mTransaction.setTotalRent(totalRent);
            mTransaction.setElectricityBill(electricityBill);
            mTransaction.setWaterBill(waterBill);
            mTransaction.setBalance(totalRent);
            mTransaction.setRentStatus(false);
            mTransaction.setComments(mRoom.getComments());
            mDaoSession.getRentTransactionDao().insertOrReplace(mTransaction);
            mDaoSession.getRoomDao().insertOrReplace(mRoom);
            return String.valueOf(totalRent);
        }else
            return String.valueOf(ERRORCODE);
    }

    private int calculateBaseRent(Room room){
        return room.getRoomRent();
    }

    private int calculateElectricityBill(Room room, String roomReading){
        return (Integer.valueOf(roomReading) - room.getLastReading()) * 7;
    }

    private int calculateWaterBill(Room room, String meterReading, int totalMembers){
        return (((Integer.valueOf(meterReading) - room.getMainMeterReading()) * 7)/totalMembers)*room.getAdults();
    }

    @Override
    public int getLastMainMeterReading(String roomNo, String month) {
        List<Room> mList = mDaoSession.queryBuilder(Room.class).where(RoomDao.Properties.RoomNumber.eq(roomNo),
                RoomDao.Properties.Month.eq(month)).list();
        if (mList != null && mList.size() > 0){
            Room room = mList.get(0);
            return room.getMainMeterReading();
        }else
            return ERRORCODE;

    }

    @Override
    public int getLastRoomReading(String roomNo, String month) {
        List<Room> mList = mDaoSession.queryBuilder(Room.class).where(RoomDao.Properties.RoomNumber.eq(roomNo),
                RoomDao.Properties.Month.eq(month)).list();
        if (mList != null && mList.size() > 0){
            Room room = mList.get(0);
            return room.getLastReading();
        }else
            return ERRORCODE;
    }

    @Override
    public Long payRent(String roomNo, String rent, String month) {
        //Log.e("AppDbHelper","Pay rent "+month);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.getDefault());
        String timeStamp = sdf.format(Calendar.getInstance().getTime());
        Log.e("AppDbHelper","Time Stamp "+timeStamp);
        List<RentTransaction> mList = mDaoSession.queryBuilder(RentTransaction.class).where(
                RentTransactionDao.Properties.RoomNo.eq(roomNo),
                RentTransactionDao.Properties.Month.eq(month)).list();
        RentTransaction mTransaction = mList.get(0);

        List<Room> roomList = mDaoSession.queryBuilder(Room.class).where(
                RoomDao.Properties.RoomNumber.eq(roomNo),
                RoomDao.Properties.Month.eq(month)).list();
        Room room = roomList.get(0);
        room.setMainMeterReading(room.getTempMainMeterReading());
        room.setRoomReading(room.getTempRoomReading());
        int prevBalance = mTransaction.getBalance();
        int rentPaid = Integer.valueOf(rent);
        int balance = prevBalance - rentPaid;
        mTransaction.setRentPaid(rentPaid);
        mTransaction.setBalance(balance);
        mTransaction.setTimeStamp(timeStamp);
        mTransaction.setMonth(month);
        mTransaction.setComments(room.getComments());

        RentHistory rentHistory = new RentHistory();
        rentHistory.setMonth(month);
        rentHistory.setRoomNumber(room.getRoomNumber());
        rentHistory.setMonth(room.getMonth());
        rentHistory.setRentPaid(rent);
        rentHistory.setBalance(String.valueOf(balance));
        rentHistory.setTimeStamp(timeStamp);
        if (balance <= 0)
            mTransaction.setRentStatus(true);
        else
            mTransaction.setRentStatus(false);
        mDaoSession.getRentHistoryDao().insertOrReplace(rentHistory);
        mDaoSession.getRoomDao().insertOrReplace(room);
        return mDaoSession.getRentTransactionDao().insertOrReplace(mTransaction);
    }

    @Override
    public int getBalance(String roomNo, String month) {
        List<RentTransaction> mList = mDaoSession.queryBuilder(RentTransaction.class).where(
                RentTransactionDao.Properties.RoomNo.eq(roomNo),
                RentTransactionDao.Properties.Month.eq(month)).list();
        if (mList != null && mList.size() > 0)
            return mList.get(0).getBalance();
        else
            return ERRORCODE;
    }

    @Override
    public boolean getRentPaidStatus(String roomNo, String month) {
        List<RentTransaction> mList = mDaoSession.queryBuilder(RentTransaction.class).where(
                RentTransactionDao.Properties.RoomNo.eq(roomNo),
                RentTransactionDao.Properties.Month.eq(month)).list();
        return mList != null && mList.size() > 0 && mList.get(0).getRentStatus();
    }

    @Override
    public List<RentHistory> getCompleteRentRecord() {
        return mDaoSession.getRentHistoryDao().loadAll();
    }

    @Override
    public List<RentHistory> getRoomRentRecord(String roomNo) {
        return mDaoSession.queryBuilder(RentHistory.class).
                where(RentHistoryDao.Properties.RoomNumber.eq(roomNo)).list();
    }

    @Override
    public Long generateNewMonthBill(String roomNo, String month, String prevMonth) {
        int balance = 0;
        List<RentTransaction> rentTransactionList = mDaoSession.
                queryBuilder(RentTransaction.class).
                where(RentTransactionDao.Properties.RoomNo.eq(roomNo),
                RentTransactionDao.Properties.Month.eq(prevMonth)).list();
        if (rentTransactionList != null && rentTransactionList.size() > 0){
            RentTransaction rentTransaction1 = rentTransactionList.get(0);
            balance = rentTransaction1.getBalance();
        }

        RentTransaction rentTransaction = new RentTransaction();
        List<Room> mList = mDaoSession.queryBuilder(Room.class).
                where(RoomDao.Properties.RoomNumber.eq(roomNo)).list();
        if (mList != null && mList.size() >0){
            Room room = mList.get(0);
            room.setMonth(month);
            rentTransaction.setName(room.getName());
            rentTransaction.setRoomNo(roomNo);
            rentTransaction.setMonth(month);
            rentTransaction.setBalance(balance);
            mDaoSession.getRoomDao().insertOrReplace(room);
            return mDaoSession.getRentTransactionDao().insert(rentTransaction);
        }else
            return (long) -1;
    }

    @Override
    public boolean checkRoomExists(String roomNo, String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yy", Locale.getDefault());
        boolean roomExists = false;
        List<Room> mList = mDaoSession.queryBuilder(Room.class).
                where(RoomDao.Properties.RoomNumber.eq(roomNo)).list();
        if (mList.size() > 0){
            Room room = mList.get(0);
            String startMonth = room.getStartMonth();
            try {
                Date startDate = sdf.parse(startMonth);
                Date currentDate = sdf.parse(month);

                if (currentDate.after(startDate) || currentDate.equals(startDate)){
                    roomExists = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return roomExists;
    }

    @Override
    public String getElectricityBill(String roomNo, String month) {
        List<RentTransaction> transactionList = mDaoSession.queryBuilder(RentTransaction.class).
                where(RentTransactionDao.Properties.RoomNo.eq(roomNo),
                        RentTransactionDao.Properties.Month.eq(month)).list();
        if (transactionList != null && transactionList.size() > 0){
            RentTransaction mTransaction = transactionList.get(0);
            return String.valueOf(mTransaction.getElectricityBill());
        }else
            return String.valueOf(ERRORCODE);

    }

    @Override
    public String getWaterBill(String roomNo, String month) {
        List<RentTransaction> transactionList = mDaoSession.queryBuilder(RentTransaction.class).
                where(RentTransactionDao.Properties.RoomNo.eq(roomNo),
                        RentTransactionDao.Properties.Month.eq(month)).list();
        if (transactionList != null && transactionList.size() > 0){
            RentTransaction mTransaction = transactionList.get(0);
            return String.valueOf(mTransaction.getWaterBill());
        }else
            return String.valueOf(ERRORCODE);
    }
}
