package com.example.sumitlakra.rentmanager.ui.showDetails;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

import java.util.List;

public class ShowDetailsPresenter<V extends ShowDetailMvpView> extends BasePresenter<V>
        implements ShowDetailsMvpPresenter<V>  {

    public ShowDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRoomDetails(String roomNo, String month) {
        List<Room> roomList = getDataManager().getAllDetails(roomNo, month);
        if (roomList != null && roomList.size() > 0){
            Room room = roomList.get(0);
            getMvpView().setTitle(room.getRoomNumber());
            getMvpView().setName(room.getName());
            getMvpView().setTotalMembers(String.valueOf(room.getTotalMembers()));
            getMvpView().setRentDue(String.valueOf(room.getRentDue()));
            getMvpView().setMonth(room.getMonth());
            getMvpView().setRoomReading(String.valueOf(room.getRoomReading()));
            getMvpView().setMeterReading(String.valueOf(room.getMainMeterReading()));
            getMvpView().setCurrentRoomReading(String.valueOf(room.getTempRoomReading()));
            getMvpView().setCurrentMeterReading(String.valueOf(room.getTempMainMeterReading()));
            getMvpView().setComments(room.getComments());
        }else
            getMvpView().showMessage("No data available for this month");


    }
}
