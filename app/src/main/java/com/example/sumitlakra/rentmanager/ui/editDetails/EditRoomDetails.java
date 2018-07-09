package com.example.sumitlakra.rentmanager.ui.editDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;


public class EditRoomDetails extends BaseActivity implements EditDetailsMvpView{

    private EditText name, age, totalMembers, adults, roomRent, roomReading,
            mainMeterReading,rentDue;

    private EditDetailsMvpPresenter<EditDetailsMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_details);

        mPresenter = new EditDetailPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

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
                mPresenter.saveDetails("roomNo",name.getText().toString(), age.getText().toString(),
                        totalMembers.getText().toString(),adults.getText().toString(),
                        roomRent.getText().toString(), roomReading.getText().toString(),
                        mainMeterReading.getText().toString(),rentDue.getText().toString());
            }
        });
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
}
