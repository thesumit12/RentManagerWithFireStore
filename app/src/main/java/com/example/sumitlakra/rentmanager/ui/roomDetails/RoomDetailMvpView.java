package com.example.sumitlakra.rentmanager.ui.roomDetails;

import com.example.sumitlakra.rentmanager.ui.base.MvpView;

public interface RoomDetailMvpView extends MvpView {

    void setTitle(String mTitle);
    void setName(String name);
    void setTotalMembers(String members);
    void setRent(String rent);
    void setComments(String comments);
    void setRentDue(String message);
    void showReadingDialog();
    void setEmptyCommentError();

    void showPayRentDialog();

    void navigateToRentHistory();

    void showRentDetailsDialog();

    void navigateToShowDetailsActivity(String roomNo, String month);
}
