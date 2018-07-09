package com.example.sumitlakra.rentmanager.ui.main;

import android.util.Log;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V>{

    private static final String TAG = "MainPresenter";

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void plot1Clicked() {

        //Log.e("Month",getMonth()+" Year "+getYear()+" int "+getIntMonth());

            if(isViewAttached())
                getMvpView().navigateToPlot1();
    }

    @Override
    public void plot2Clicked() {
        getMvpView().navigateToPlot2();
    }
}
