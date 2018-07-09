package com.example.sumitlakra.rentmanager.ui.addRoomProfile;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RoomProfileMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void addRoomClicked(String roomNo, String name, String age, String totalMembers, String adults,
                        String baseRent, String roomReading, String mainMeterReading, String rentDue,
                        String month, int monthPosition);

    void setSpinner();
}
