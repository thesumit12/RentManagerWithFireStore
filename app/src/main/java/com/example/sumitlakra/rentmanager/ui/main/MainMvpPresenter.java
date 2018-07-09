package com.example.sumitlakra.rentmanager.ui.main;

import com.example.sumitlakra.rentmanager.ui.base.MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface MainMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void plot1Clicked();

    void plot2Clicked();

}
