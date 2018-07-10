package com.example.sumitlakra.rentmanager.ui.showDetails;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface ShowDetailMvpView extends MvpView {

    void setTitle(String roomTitle);
    void setName(String name);
    void setTotalMembers(String totalMembers);
    void setRentDue(String rentDue);
    void setMonth(String month);
    void setRoomReading(String roomReading);
    void setMeterReading(String meterReading);
    void setCurrentRoomReading(String currentRoomReading);
    void setCurrentMeterReading(String currentMeterReading);
    void setComments(String comments);

}
