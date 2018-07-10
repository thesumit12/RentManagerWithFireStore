package com.example.sumitlakra.rentmanager.ui.showDetails;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;

public class ShowDetailsActivity extends BaseActivity implements ShowDetailMvpView {

    private TextView tvName, tvTotalMembers, tvMonth, tvTitle, tvDueOn, tvRoomReading, tvMeterReading,
    tvCurrentRoomReading, tvCurrentMeterReading, tvComments;

    private ShowDetailsMvpPresenter<ShowDetailMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        if (getIntent().getExtras() != null){
            String mTitle = getIntent().getStringExtra("ROOMNO");
            String mMonth = getIntent().getStringExtra("MONTH");

            mPresenter = new ShowDetailsPresenter<>(getAppDataManager());
            mPresenter.onAttach(this);

            tvTitle = findViewById(R.id.sd_tv_title);
            tvTotalMembers = findViewById(R.id.sd_tv_member_right);
            tvMonth = findViewById(R.id.sd_tv_month_right);
            tvDueOn = findViewById(R.id.sd_tv_rent_due_right);
            tvRoomReading = findViewById(R.id.sd_tv_room_reading_right);
            tvMeterReading = findViewById(R.id.sd_tv_meter_reading_right);
            tvCurrentRoomReading = findViewById(R.id.sd_tv_room_temp_reading_right);
            tvCurrentMeterReading = findViewById(R.id.sd_tv_meter_temp_reading_right);
            tvComments = findViewById(R.id.sd_tv_comments);
            tvName = findViewById(R.id.sd_tv_name_right);

            mPresenter.getRoomDetails(mTitle, mMonth);

        }
    }


    @Override
    public void setTitle(String roomTitle) {
        tvTitle.setText(roomTitle);
    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setTotalMembers(String totalMembers) {
        tvTotalMembers.setText(totalMembers);
    }

    @Override
    public void setRentDue(String rentDue) {
        tvDueOn.setText(rentDue);
    }

    @Override
    public void setMonth(String month) {
        tvMonth.setText(month);
    }

    @Override
    public void setRoomReading(String roomReading) {
        tvRoomReading.setText(roomReading);
    }

    @Override
    public void setMeterReading(String meterReading) {
        tvMeterReading.setText(meterReading);
    }

    @Override
    public void setCurrentRoomReading(String currentRoomReading) {
        tvCurrentRoomReading.setText(currentRoomReading);
    }

    @Override
    public void setCurrentMeterReading(String currentMeterReading) {
        tvCurrentMeterReading.setText(currentMeterReading);
    }

    @Override
    public void setComments(String comments) {
        tvComments.setText(comments);
    }
}
