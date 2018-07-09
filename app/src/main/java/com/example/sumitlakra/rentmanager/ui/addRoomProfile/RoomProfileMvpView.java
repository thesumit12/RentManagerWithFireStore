package com.example.sumitlakra.rentmanager.ui.addRoomProfile;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RoomProfileMvpView extends MvpView {

    void setRoomNumberError();

    void setNameError();

    void setAgeError();

    void setTotalMembersError();

    void setAdultsError();

    void setRoomRentError();

    void setRoomReadingError();

    void setMainMeterReadingError();

    void setRentDueError();

    void navigateToPlot737Activity();

    void setSpinner();

    void setWrongMonthError();
}
