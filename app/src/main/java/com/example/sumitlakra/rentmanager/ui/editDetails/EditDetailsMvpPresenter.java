package com.example.sumitlakra.rentmanager.ui.editDetails;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface EditDetailsMvpPresenter<V extends EditDetailsMvpView> extends MvpPresenter<V> {

    void saveDetails(String roomNo, String name, String age, String totalMembers, String adults,
                        String baseRent, String roomReading, String mainMeterReading, String rentDue);

    void getDetails(String mRoomNo);
}
