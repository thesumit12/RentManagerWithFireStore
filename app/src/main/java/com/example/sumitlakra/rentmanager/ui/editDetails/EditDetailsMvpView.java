package com.example.sumitlakra.rentmanager.ui.editDetails;

import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface EditDetailsMvpView extends MvpView {

    void setNameError();

    void setAgeError();

    void setTotalMembersError();

    void setAdultsError();

    void setRoomRentError();

    void setRoomReadingError();

    void setMainMeterReadingError();

    void setRentDueError();

    void setDetailData(Room room);

    void finishActivity();
}
