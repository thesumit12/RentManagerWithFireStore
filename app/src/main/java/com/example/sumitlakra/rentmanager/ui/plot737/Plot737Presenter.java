package com.example.sumitlakra.rentmanager.ui.plot737;
import android.os.Environment;

import com.example.sumitlakra.rentmanager.data.DataManager;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BasePresenter;

import java.io.File;
import java.nio.channels.FileChannel;
import java.util.List;

public class Plot737Presenter<V extends Plot737MvpView> extends BasePresenter<V>
        implements Plot737MvpPresenter<V> {

    private static final String TAG = "Plot737Presenter";
    private List<Room> mRoomList;

    public Plot737Presenter(DataManager dataManager){
        super(dataManager);
    }

    @Override
    public void addRoomClicked() {
            if (isViewAttached())
                getMvpView().navigateToAddRoomProfile();
    }

    @Override
    public void deleteRoomClicked() {
        if (isViewAttached())
            getMvpView().showRoomDialog("Delete Room");
    }

    @Override
    public void rentHistoryClicked() {
        if (isViewAttached())
            getMvpView().navigateToRentHistory();
    }

    @Override
    public void getRoomList() {
        mRoomList = getDataManager().getRoomList();
        getMvpView().refreshView();
        if (mRoomList != null && mRoomList.size() <= 0)
            getMvpView().showMessage("No Rooms Available!!");
    }

    @Override
    public void onBindRoomRowViewAtPosition(int position, RoomRowView rowView) {
        Room room = mRoomList.get(position);
        rowView.setImage(room.getImageId());
        rowView.setTitle(room.getRoomNumber());
    }

    @Override
    public int getRoomRowsCount() {
        return mRoomList.size();
    }

    @Override
    public void onItemInteraction(int position) {
        //getMvpView().showMessage("Item Clicked : "+position);
        Room room = mRoomList.get(position);
        getMvpView().showDetails(room.getRoomNumber());
    }

    @Override
    public void createBackup(String currentDbPath) {
        //TODO: write code for export and import
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();

        FileChannel source, destination = null;
    }
}
