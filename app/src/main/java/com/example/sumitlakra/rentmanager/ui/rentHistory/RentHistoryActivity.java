package com.example.sumitlakra.rentmanager.ui.rentHistory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.rentHistory.adapter.RentHistoryAdapter;

import java.util.Objects;

public class RentHistoryActivity extends BaseActivity implements RentHistoryMvpView{

    private RentHistoryMvpPresenter<RentHistoryMvpView> mPresenter;
    private RentHistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_history);

        mPresenter = new RentHistoryPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        String  roomNo = "Room 1";
        boolean isFetchRoomList = false;
        if (getIntent().getExtras() != null){
            roomNo = Objects.requireNonNull(getIntent().getExtras()).getString("ROOMNO");
            isFetchRoomList = getIntent().getExtras().getBoolean("ROOMLIST");
        }


        RecyclerView mRecyclerView = findViewById(R.id.rent_history_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(
                this, LinearLayoutManager.VERTICAL, 0));
        mAdapter = new RentHistoryAdapter(mPresenter);
        mRecyclerView.setAdapter(mAdapter);
        if (isFetchRoomList)
            mPresenter.getRoomRentHistory(roomNo);
        else
            mPresenter.getAllRoomRentHistory();
    }

    @Override
    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onDetach();
        this.finish();
    }
}
