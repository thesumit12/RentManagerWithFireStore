package com.example.sumitlakra.rentmanager.ui.rentHistory;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RentHistoryMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void getRoomRentHistory(String roomNo);

    void getAllRoomRentHistory();

    void onBindRoomRowViewAtPosition(int position, RentHistoryRowView rowView);

    int getRoomRowsCount();
}
