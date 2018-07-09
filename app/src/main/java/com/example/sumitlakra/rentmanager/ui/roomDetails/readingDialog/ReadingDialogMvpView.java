package com.example.sumitlakra.rentmanager.ui.roomDetails.readingDialog;

import com.example.sumitlakra.rentmanager.ui.base.DialogMvpView;

public interface ReadingDialogMvpView extends DialogMvpView {

    void dismissDialog();

    void setMainReadingEmptyError();

    void setRoomReadingEmptyError();

    void setWrongMainReadingError();

    void setWrongRoomReadingError();

    void setTitle();

    void refreshView(String totalRent, String message);

    void setPreviousReadings(int mainReading, int roomReading);
}
