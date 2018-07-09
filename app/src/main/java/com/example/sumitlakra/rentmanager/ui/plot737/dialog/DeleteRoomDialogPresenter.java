package com.example.sumitlakra.rentmanager.ui.plot737.dialog;

import android.text.TextUtils;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

public class DeleteRoomDialogPresenter<V extends DeleteRoomDialogMvpView> extends BasePresenter<V>
        implements DeleteRoomDialogMvpPresenter<V> {

    public DeleteRoomDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onRoomNoSubmitted(String roomNo) {
        if (TextUtils.isEmpty(roomNo)){
            getMvpView().setRoomEmptyError();
        }else{
            getMvpView().dismissDialog();
            Long id = getDataManager().deleteRoom("Room "+roomNo);
            if (id > 0){
                getMvpView().refreshView();
            }else
                getMvpView().showMessage("Error while deleting room");
        }
    }

    @Override
    public void onCancelClicked() {
        getMvpView().dismissDialog();
    }

}
