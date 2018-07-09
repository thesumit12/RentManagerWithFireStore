package com.example.sumitlakra.rentmanager.ui.plot737.dialog;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;

public interface DeleteRoomDialogMvpPresenter<V extends DeleteRoomDialogMvpView> extends MvpPresenter<V> {

    void onRoomNoSubmitted(String roomNo);

    void onCancelClicked();
}
