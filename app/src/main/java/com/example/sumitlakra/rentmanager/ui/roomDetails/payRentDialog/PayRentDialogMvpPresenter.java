package com.example.sumitlakra.rentmanager.ui.roomDetails.payRentDialog;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface PayRentDialogMvpPresenter<V extends PayRentDialogMvpView> extends MvpPresenter<V> {

    void onRentSubmitted(String roomNo, String rent, String month);

    void onCancelClicked();
}
