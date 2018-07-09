package com.example.sumitlakra.rentmanager.ui.roomDetails.readingDialog;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface ReadingDialogMvpPresenter<V extends ReadingDialogMvpView> extends MvpPresenter<V> {

    void cancelClicked();

    void submitClicked(String mainReading, String roomReading, String mTitle, String month);

    void setUpTitle();

    void getPreviousReading(String roomNo, String month);
}
