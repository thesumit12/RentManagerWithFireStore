package com.example.sumitlakra.rentmanager.ui.base;

import android.util.Log;

import com.example.sumitlakra.rentmanager.data.DataManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;

    private V mMvpView;

    public BasePresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }


    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    protected V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        throw new MvpViewNotAttachedException();
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }

    protected String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return sdf.format(calendar.getTime());
    }

    protected String getPreviousMonth(int monthPosition){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, monthPosition);
        calendar.add(Calendar.MONTH, -1);
        Log.e(TAG,"Month : "+sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime()); // return previous month
    }

    protected int getPreviousMonthPosition(){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH);
    }

    protected String getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yy", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return sdf.format(calendar.getTime());
    }

    protected int getIntMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
