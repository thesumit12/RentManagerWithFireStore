package com.example.sumitlakra.rentmanager.ui.plot737.dialog;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface DeleteRoomDialogMvpView extends MvpView {

    void setRoomEmptyError();

    void dismissDialog();

    void refreshView();

}
