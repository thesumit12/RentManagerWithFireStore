package com.example.sumitlakra.rentmanager.ui.roomDetails.payRentDialog;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface PayRentDialogMvpView extends MvpView {

    void setRentEmptyError();

    void dismissDialog();

    void refreshView(String balance, String message);

}
