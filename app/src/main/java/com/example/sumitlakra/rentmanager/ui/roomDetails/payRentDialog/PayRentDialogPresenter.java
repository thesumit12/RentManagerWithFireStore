package com.example.sumitlakra.rentmanager.ui.roomDetails.payRentDialog;

import android.text.TextUtils;
import android.util.Log;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

public class PayRentDialogPresenter<V extends PayRentDialogMvpView> extends BasePresenter<V>
        implements PayRentDialogMvpPresenter<V> {

    public PayRentDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onRentSubmitted(String roomNo, String rent, String month) {
        if (TextUtils.isEmpty(rent)){
            getMvpView().setRentEmptyError();
        }else{
            getMvpView().dismissDialog();
            //pay rent
            Long id = getDataManager().payRent(roomNo, rent, month+" "+getYear());
            if (id != -1){
                int balance = getDataManager().getBalance(roomNo, month+" "+getYear());
                //Log.e("PayRent","Balance is "+balance);
                if (balance > 0)
                    getMvpView().refreshView(String.valueOf(balance), "Partial Rent paid");
                else
                    getMvpView().refreshView(String.valueOf(balance), "Rent Paid");
            }else
                getMvpView().showMessage("Error while paying rent");
        }
    }

    @Override
    public void onCancelClicked() {
        getMvpView().dismissDialog();
    }

}
