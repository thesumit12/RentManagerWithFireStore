package com.example.sumitlakra.rentmanager.ui.plot737;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface Plot737MvpPresenter<V extends MvpView> extends MvpPresenter<V>{

    void addRoomClicked();

    void deleteRoomClicked();

    void rentHistoryClicked();

    void getRoomList();

    void onBindRoomRowViewAtPosition(int position, RoomRowView rowView);

    int getRoomRowsCount();

    void onItemInteraction(int position);

    void createBackup(String currentPath);

}
