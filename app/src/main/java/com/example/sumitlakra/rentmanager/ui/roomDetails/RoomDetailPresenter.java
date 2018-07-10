package com.example.sumitlakra.rentmanager.ui.roomDetails;

import android.text.TextUtils;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RoomDetailPresenter<V extends RoomDetailMvpView> extends BasePresenter<V>
        implements RoomDetailMvpPresenter<V> {

    private static final int ERRORCODE = 9999;
    private static boolean firstTime = true;
    private static String startMonth;

    public RoomDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRoomDetails(String roomNo, String month) {
        List<Room> roomList = getDataManager().getRoomDetails(roomNo, month+" "+getYear());
        if (roomList.size() > 0){
            if (roomList.get(0).getName() != null)
                getMvpView().setName(roomList.get(0).getName());
            else
                getMvpView().setName("--");

            if (roomList.get(0).getTotalMembers() != 0)
                getMvpView().setTotalMembers(String.valueOf(roomList.get(0).getTotalMembers()));
            else
                getMvpView().setTotalMembers("--");

            if (roomList.get(0).getComments() != null)
                getMvpView().setComments(roomList.get(0).getComments());
            else
                getMvpView().setComments(" ");

            int balance = getDataManager().getBalance(roomNo, month+" "+getYear());
            if (balance == 1)
                getMvpView().setRent("--");
            else if (balance == ERRORCODE){
                getMvpView().setRent("--");
                if (firstTime){
                    getMvpView().showMessage("Rent data is available from "
                            +roomList.get(0).getMonth());
                    firstTime = false;
                }
            }
            else
                getMvpView().setRent(String.valueOf(balance));

            if (balance <= 0)
                getMvpView().setRentDue("Rent Paid");
            else
                getMvpView().setRentDue(roomList.get(0).getRentDue()+" of each month");

            startMonth = roomList.get(0).getStartMonth();

        }else
            getMvpView().showMessage("No data found for "+roomNo);

    }

    @Override
    public void setRoomTitle(String title) {
        getMvpView().setTitle(title);
    }

    @Override
    public void saveComments(String mComments, String roomNo, String month) {
        if (TextUtils.isEmpty(mComments)){
            getMvpView().setEmptyCommentError();
        }else if (month == null)
            getMvpView().showMessage("Please select appropriate month");
        else{
            Long id = getDataManager().saveComment(mComments, roomNo, month+" "+getYear());
            if (id != ERRORCODE)
                getMvpView().showMessage("Comment saved successfully");
            else
                getMvpView().showMessage("Error saving comment!!");
        }

    }

    @Override
    public void calculateRentClicked(String roomNo, String month, int monthPosition) {
        long id;

        if (monthPosition > getIntMonth())
            getMvpView().showMessage("Selected month can't be greater than current month");
        else {
            if (getDataManager().checkRoomExists(roomNo, month+" "+getYear())) {
                id = getDataManager().generateNewMonthBill(roomNo, month+" "+getYear(),
                        getPreviousMonth(monthPosition) + " " + getYear());
            }else
                id = 0;

            if (id != -1){
                //first time calculating rent
                if (getDataManager().getRentPaidStatus(roomNo, month + " " + getYear())){
                    getMvpView().showMessage("Rent Paid for this Month");
                    getMvpView().setRentDue("Rent Paid");
                    getMvpView().setRent(String.valueOf(0));
                }else{
                    int balance = getDataManager().getBalance(roomNo, month + " " + getYear());
                    if (balance == ERRORCODE)
                        getMvpView().showMessage("Rent starts from "+startMonth);
                    else
                        getMvpView().showReadingDialog();
                }
            }else
                getMvpView().showMessage("Error calculating Rent");
        }
    }

    @Override
    public void payRentClicked(String roomNo, String month, int monthPosition) {

        if (monthPosition > getIntMonth())
            getMvpView().showMessage("Selected month can't be greater than current month");
        else{
            int balance = getDataManager().getBalance(roomNo, month+" "+getYear());

            if (balance == 1 || balance == ERRORCODE)
                getMvpView().showMessage("Please calculate Rent first");
            else if (balance <= 0){
                getMvpView().setRentDue("Rent Paid");
                getMvpView().setRent(String.valueOf(balance));
                getMvpView().showMessage("Rent Paid for this Month");
            }
            else{
                getMvpView().showPayRentDialog();
            }

        }

    }

    @Override
    public void roomRentHistoryClicked() {
        getMvpView().navigateToRentHistory();
    }

    @Override
    public void showRentDetails() {
        getMvpView().showRentDetailsDialog();
    }

    @Override
    public void roomDetailsClicked(String roomNo, String month) {
        if (getDataManager().checkRoomExists(roomNo, month+" "+getYear()))
            getMvpView().navigateToShowDetailsActivity(roomNo, month+" "+getYear());
        else
            getMvpView().showMessage("Details available from "+startMonth);
    }
}
