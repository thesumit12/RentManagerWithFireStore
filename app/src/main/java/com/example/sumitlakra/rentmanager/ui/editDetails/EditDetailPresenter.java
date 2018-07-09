package com.example.sumitlakra.rentmanager.ui.editDetails;

import android.text.TextUtils;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

public class EditDetailPresenter<V extends EditDetailsMvpView> extends
        BasePresenter<V> implements EditDetailsMvpPresenter<V>{

    public EditDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void saveDetails(String roomNo,String name, String age, String totalMembers, String adults,
                            String baseRent, String roomReading, String mainMeterReading,
                            String rentDue) {

        if (TextUtils.isEmpty(name))
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
        else
            getDataManager().saveNewDetails(new Room("Room "+roomNo,name,Integer.valueOf(age),
                    Integer.valueOf(totalMembers),Integer.valueOf(adults),Integer.valueOf(baseRent),
                    Integer.valueOf(roomReading),Integer.valueOf(mainMeterReading),Integer.valueOf(rentDue)));
            //add room

    }
}
