package com.example.sumitlakra.rentmanager.ui.addRoomProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;

public class RoomProfileActivity extends BaseActivity implements RoomProfileMvpView, OnItemSelectedListener {

    private EditText roomNumber, name, age, totalMembers, adults, roomRent, roomReading,
            mainMeterReading,rentDue;
    private Spinner spinner;
    private String month;
    private int monthPosition;

    private RoomProfileMvpPresenter<RoomProfileMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RoomProfilePresenter<>(getAppDataManager());
        mPresenter.onAttach(this);
        setContentView(R.layout.activity_room_profile);
        roomNumber = findViewById(R.id.rf_et_room_no);
        name = findViewById(R.id.rf_et_name);
        age = findViewById(R.id.rf_et_age);
        totalMembers = findViewById(R.id.rf_et_total_members);
        adults = findViewById(R.id.rf_et_adults);
        roomRent = findViewById(R.id.rf_et_base_rent);
        roomReading = findViewById(R.id.rf_et_last_reading);
        mainMeterReading = findViewById(R.id.rf_et_main_meter_reading);
        rentDue = findViewById(R.id.rf_et_rent_due);
        spinner = findViewById(R.id.month_spinner);
        spinner.setOnItemSelectedListener(this);


        Button addRoom = findViewById(R.id.rf_btn_add);

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addRoomClicked(roomNumber.getText().toString(),name.getText().toString(),
                        age.getText().toString(),totalMembers.getText().toString(),adults.getText().toString(),
                        roomRent.getText().toString(), roomReading.getText().toString(),
                        mainMeterReading.getText().toString(),rentDue.getText().toString(),month,
                        monthPosition);
            }
        });

        mPresenter.setSpinner();
    }

    @Override
    public void setRoomNumberError() {
        roomNumber.requestFocus();
        roomNumber.setError("Room Number Empty");
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
    public void navigateToPlot737Activity() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void setWrongMonthError() {
        showMessage("Selected Month can't be greater than current month");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        month = parent.getItemAtPosition(position).toString();
        monthPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
