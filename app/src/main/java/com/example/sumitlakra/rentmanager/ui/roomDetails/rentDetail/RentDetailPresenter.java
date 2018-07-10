package com.example.sumitlakra.rentmanager.ui.roomDetails.rentDetail;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

public class RentDetailPresenter<V extends RentDetailMvpView> extends BasePresenter<V>
        implements RentDetailMvpPresenter<V>{

    public RentDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWaterBill(String roomNo, String month) {

        String waterBill = getDataManager().getWaterBill(roomNo, month+" "+getYear());

        if (waterBill == null)
            getMvpView().setWaterBill("--");
        else if (waterBill.equals("9999"))
            getMvpView().showMessage("bill not available for "+month);
        else
            getMvpView().setWaterBill(waterBill);
    }

    @Override
    public void getElectricityBill(String roomNo, String month) {
        String electricityBill = getDataManager().getElectricityBill(roomNo, month+" "+getYear());
        if (electricityBill == null)
            getMvpView().setWaterBill("--");
        else if (electricityBill.equals("9999"))
            getMvpView().showMessage("bill not available for "+month);
        else
            getMvpView().setElectricityBill(electricityBill);
    }

    @Override
    public void setTitle(String mTitle, String month) {
        getMvpView().setDialogTitle(mTitle+" "+month+" "+getYear());
    }

    @Override
    public void getBalance(String roomNo, int monthPosition) {
        int balance = getDataManager().getBalance(roomNo, getPreviousMonth(monthPosition)+" "+getYear());
        if (balance == 9999)
            getMvpView().setBalance("--");
        else
            getMvpView().setBalance(String.valueOf(balance));
    }

}
