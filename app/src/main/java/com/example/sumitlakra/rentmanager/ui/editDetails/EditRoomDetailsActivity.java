package com.example.sumitlakra.rentmanager.ui.editDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.data.db.model.Room;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737Activity;


public class EditRoomDetailsActivity extends BaseActivity implements EditDetailsMvpView{

    private EditText name, age, totalMembers, adults, roomRent, roomReading,
            mainMeterReading,rentDue;
    private String mRoomNo;

    private EditDetailsMvpPresenter<EditDetailsMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_details);

        mPresenter = new EditDetailPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        if (getIntent().getExtras() != null)
            mRoomNo = getIntent().getStringExtra("ROOMNO");

        name = findViewById(R.id.ed_et_name);
        age = findViewById(R.id.ed_et_age);
        totalMembers = findViewById(R.id.ed_et_total_members);
        adults = findViewById(R.id.ed_et_adults);
        roomRent = findViewById(R.id.ed_et_base_rent);
        roomReading = findViewById(R.id.ed_et_last_reading);
        mainMeterReading = findViewById(R.id.ed_et_main_meter_reading);
        rentDue = findViewById(R.id.ed_et_rent_due);

        Button save = findViewById(R.id.ed_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.saveDetails(mRoomNo,name.getText().toString(), age.getText().toString(),
                        totalMembers.getText().toString(),adults.getText().toString(),
                        roomRent.getText().toString(), roomReading.getText().toString(),
                        mainMeterReading.getText().toString(),rentDue.getText().toString());
            }
        });

        mPresenter.getDetails(mRoomNo);
    }

    @Override
    public void setNameError() {
        name.requestFocus();
        name.setError("Name Empty");
    }

    @Override
    public void setAgeError() {
        age.requestFocus();
        age.setError("Age Empty");
    }

    @Override
    public void setTotalMembersError() {
        totalMembers.requestFocus();
        totalMembers.setError("Total Members Empty");
    }

    @Override
    public void setAdultsError() {
        adults.requestFocus();
        adults.setError("Adults Empty");
    }

    @Override
    public void setRoomRentError() {
        roomRent.requestFocus();
        roomRent.setError("Room Rent Empty");
    }

    @Override
    public void setRoomReadingError() {
        roomReading.requestFocus();
        roomReading.setError("Last Reading Empty");
    }

    @Override
    public void setMainMeterReadingError() {
        mainMeterReading.requestFocus();
        mainMeterReading.setError("Main Meter Reading empty");
    }

    @Override
    public void setRentDueError() {
        rentDue.requestFocus();
        rentDue.setError("Rent due empty");
    }

    @Override
    public void setDetailData(Room room) {
        name.setText(room.getName());
        age.setText(String.valueOf(room.getAge()));
        totalMembers.setText(String.valueOf(room.getTotalMembers()));
        adults.setText(String.valueOf(room.getAdults()));
        roomRent.setText(String.valueOf(room.getRoomRent()));
        roomReading.setText(String.valueOf(room.getRoomReading()));
        mainMeterReading.setText(String.valueOf(room.getMainMeterReading()));
        rentDue.setText(String.valueOf(room.getRentDue()));
    }

    @Override
    public void finishActivity() {
        Intent intent = new Intent(EditRoomDetailsActivity.this, Plot737Activity.class);
        intent.putExtra("NAME","PLOT 737");
        startActivity(intent);
        this.finish();
    }
}
