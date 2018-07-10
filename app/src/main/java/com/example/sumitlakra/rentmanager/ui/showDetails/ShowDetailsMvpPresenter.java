package com.example.sumitlakra.rentmanager.ui.showDetails;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface ShowDetailsMvpPresenter<V extends ShowDetailMvpView> extends MvpPresenter<V> {

    void getRoomDetails(String roomNo, String month);
}
