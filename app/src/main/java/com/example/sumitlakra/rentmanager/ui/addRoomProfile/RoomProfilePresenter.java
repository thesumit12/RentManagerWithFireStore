package com.example.sumitlakra.rentmanager.ui.addRoomProfile;

import android.database.sqlite.SQLiteConstraintException;
import android.text.TextUtils;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RoomProfilePresenter<V extends RoomProfileMvpView> extends BasePresenter<V>
        implements RoomProfileMvpPresenter<V> {


    public RoomProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void addRoomClicked(String roomNo, String name, String age, String totalMembers,
                               String adults, String baseRent, String roomReading,
                               String mainMeterReading, String rentDue, String month,
                               int monthPosition) {
        if (TextUtils.isEmpty(roomNo))
            getMvpView().setRoomNumberError();
        else if (TextUtils.isEmpty(name))
            getMvpView().setNameError();
        else if (TextUtils.isEmpty(age))
            getMvpView().setAgeError();
        else if (TextUtils.isEmpty(totalMembers))
            getMvpView().setTotalMembersError();
        else if (TextUtils.isEmpty(adults))
            getMvpView().setAdultsError();
        else if (TextUtils.isEmpty(baseRent))
            getMvpView().setRoomRentError();
        else if (TextUtils.isEmpty(roomReading))
            getMvpView().setRoomReadingError();
        else if (TextUtils.isEmpty(mainMeterReading))
            getMvpView().setMainMeterReadingError();
        else if (TextUtils.isEmpty(rentDue))
            getMvpView().setRentDueError();
        else if (monthPosition > getIntMonth())
            getMvpView().setWrongMonthError();
        else {
            long id = 0;
            try {
                id = getDataManager().addRoom(new Room(R.drawable.room_icon, "Room " + roomNo, name,
                        Integer.valueOf(age), Integer.valueOf(totalMembers), Integer.valueOf(adults),
                        Integer.valueOf(baseRent), Integer.valueOf(roomReading), Integer.valueOf(mainMeterReading),
                        Integer.valueOf(rentDue), 0, null,
                        month+" "+getYear(), month+" "+getYear(),Integer.valueOf(roomNo)));
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
                getMvpView().showMessage("Error Room No can't be same!!");
            }
            if (id != -1){
                getMvpView().navigateToPlot737Activity();
            }else{
                getMvpView().showMessage("Error while adding new room!!");
            }
        }


    }

    @Override
    public void setSpinner() {
        getMvpView().setSpinner();
    }
}
