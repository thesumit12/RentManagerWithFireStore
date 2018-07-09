package com.example.sumitlakra.rentmanager.ui.plot737;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface Plot737MvpView extends MvpView{

    void showRoomDialog(String title);

    void navigateToAddRoomProfile();

    void getUpdatedList();

    void showDetails(String title);

    void refreshView();

    void navigateToRentHistory();

    void createBackUpClicked();
}
