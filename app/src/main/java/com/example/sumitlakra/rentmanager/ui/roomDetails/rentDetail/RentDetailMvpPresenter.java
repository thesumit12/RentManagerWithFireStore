package com.example.sumitlakra.rentmanager.ui.roomDetails.rentDetail;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface RentDetailMvpPresenter<V extends RentDetailMvpView> extends MvpPresenter<V> {

    void getWaterBill(String roomNo, String month);

    void getElectricityBill(String roomNo, String month);

    void setTitle(String mTitle, String month);

    void getBalance(String roomNo, int monthPosition);
}
