package com.example.sumitlakra.rentmanager.ui.plot737;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.addRoomProfile.RoomProfileActivity;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.plot737.adapter.RoomAdapter;
import com.example.sumitlakra.rentmanager.ui.plot737.dialog.DeleteRoomDialog;
import com.example.sumitlakra.rentmanager.ui.rentHistory.RentHistoryActivity;
import com.example.sumitlakra.rentmanager.ui.roomDetails.RoomDetailActivity;

import java.util.Objects;

public class Plot737Activity extends BaseActivity implements Plot737MvpView {

    private Plot737MvpPresenter<Plot737MvpView> mPresenter;
    private RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot737);

        mPresenter = new Plot737Presenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        TextView heading = findViewById(R.id.tv_heading);
        RecyclerView mRecyclerView = findViewById(R.id.room_recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RoomAdapter(mPresenter);
        mRecyclerView.setAdapter(adapter);
        //get the list
        mPresenter.getRoomList();
        heading.setText(Objects.requireNonNull(getIntent().getExtras()).getString("NAME"));

    }

    @Override
    public void refreshView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToRentHistory() {
        startActivity(new Intent(Plot737Activity.this, RentHistoryActivity.class));
    }

    @Override
    public void createBackUpClicked() {
        String currentDbPath = getApplicationContext().getDatabasePath("{rooms.db}").getAbsolutePath();
        mPresenter.createBackup(currentDbPath);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                mPresenter.addRoomClicked();
                return true;
            case R.id.delete_item:
                mPresenter.deleteRoomClicked();
                return true;
            case R.id.rent_history:
                mPresenter.rentHistoryClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showRoomDialog(String title) {
        DialogFragment dialogFragment = DeleteRoomDialog.newInstance(title);
        dialogFragment.show(getSupportFragmentManager(),"PayRentDialog");
    }

    @Override
    public void navigateToAddRoomProfile() {
        startActivityForResult(new Intent(Plot737Activity.this, RoomProfileActivity.class), 1);
    }

    @Override
    public void getUpdatedList() {
        mPresenter.getRoomList();
    }

    @Override
    public void showDetails(String title) {
        Intent intent = new Intent(Plot737Activity.this, RoomDetailActivity.class);
        intent.putExtra("TITLE",title);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_CANCELED){
                mPresenter.getRoomList();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
