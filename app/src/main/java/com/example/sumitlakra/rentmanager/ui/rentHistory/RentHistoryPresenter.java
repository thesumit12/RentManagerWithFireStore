package com.example.sumitlakra.rentmanager.ui.rentHistory;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.RentHistory;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

import java.util.List;

public class RentHistoryPresenter<V extends RentHistoryMvpView> extends BasePresenter<V>
        implements RentHistoryMvpPresenter<V>{

    private List<RentHistory> mList;

    public RentHistoryPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRoomRentHistory(String roomNo) {
        mList = getDataManager().getRoomRentRecord(roomNo);
        if (mList.size() > 0)
            getMvpView().refreshList();
        else
            getMvpView().showMessage("No Record found for "+roomNo);
    }

    @Override
    public void getAllRoomRentHistory() {
        //get the list and refresh the view
        mList = getDataManager().getCompleteRentRecord();
        if (mList.size() > 0)
            getMvpView().refreshList();
        else
            getMvpView().showMessage("No Record found");
    }

    @Override
    public void onBindRoomRowViewAtPosition(int position, RentHistoryRowView rowView) {
        RentHistory rentHistory = mList.get(position);
        rowView.setRoomNo(rentHistory.getRoomNumber());
        rowView.setMonth(rentHistory.getMonth());
        rowView.setRentPaid(rentHistory.getRentPaid());
        rowView.setBalance(rentHistory.getBalance());
        rowView.setTimeStamp(rentHistory.getTimeStamp());
    }

    @Override
    public int getRoomRowsCount() {
        return mList.size();
    }
}
