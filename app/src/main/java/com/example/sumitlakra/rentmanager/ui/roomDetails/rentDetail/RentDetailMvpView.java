package com.example.sumitlakra.rentmanager.ui.roomDetails.rentDetail;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RentDetailMvpView extends MvpView {
    void setWaterBill(String waterBill);

    void setElectricityBill(String electricityBill);

    void setDialogTitle(String dialogTitle);

    void setBalance(String balance);
}
