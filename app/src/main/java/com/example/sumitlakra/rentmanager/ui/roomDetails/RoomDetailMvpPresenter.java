package com.example.sumitlakra.rentmanager.ui.roomDetails;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RoomDetailMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void getRoomDetails(String roomNo, String month);

    void setRoomTitle(String title);

    void saveComments(String mComments, String roomNo, String month);

    void calculateRentClicked(String roomNo, String month, int monthPosition);

    void payRentClicked(String roomNo, String month, int monthPosition);

    void roomRentHistoryClicked();

    void showRentDetails();
}
