package com.example.sumitlakra.rentmanager.ui.roomDetails.readingDialog;

import android.text.TextUtils;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

public class ReadingDialogPresenter<V extends ReadingDialogMvpView> extends BasePresenter<V>
        implements ReadingDialogMvpPresenter<V>{

    public ReadingDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void cancelClicked() {
        getMvpView().dismissDialog();
    }

    @Override
    public void submitClicked(String mainReading, String roomReading, String roomNo, String month) {
        if (TextUtils.isEmpty(mainReading))
            getMvpView().setMainReadingEmptyError();
        else if (TextUtils.isEmpty(roomReading))
            getMvpView().setRoomReadingEmptyError();
        else if (Integer.valueOf(mainReading) < getDataManager().getLastMainMeterReading(roomNo, month+" "+getYear()))
            getMvpView().setWrongMainReadingError();
        else if (Integer.valueOf(roomReading) < getDataManager().getLastRoomReading(roomNo, month+" "+getYear()))
            getMvpView().setWrongRoomReadingError();
        else {
            getMvpView().dismissDialog();
            String totalRent = getDataManager().calculateRent(mainReading, roomReading, roomNo, month+" "+getYear());
            String ERRORCODE = "9999";
            if (totalRent.equals(ERRORCODE)){
                getMvpView().showMessage("Error while calculating rent");
            }else{
                if (Integer.valueOf(totalRent) > 0)
                    getMvpView().refreshView(totalRent, "Due for this month");
                else
                    getMvpView().refreshView(totalRent, "Rent Paid");
            }

        }


    }

    @Override
    public void setUpTitle() {
        getMvpView().setTitle();
    }

    @Override
    public void getPreviousReading(String roomNo, String month) {
        getMvpView().setPreviousReadings(getDataManager().getLastMainMeterReading(roomNo,month+" "+getYear()),
                getDataManager().getLastRoomReading(roomNo, month+" "+getYear()));
    }


}
