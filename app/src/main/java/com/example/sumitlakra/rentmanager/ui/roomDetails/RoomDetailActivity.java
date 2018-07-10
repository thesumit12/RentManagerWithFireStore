package com.example.sumitlakra.rentmanager.ui.roomDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.editDetails.EditRoomDetailsActivity;
import com.example.sumitlakra.rentmanager.ui.rentHistory.RentHistoryActivity;
import com.example.sumitlakra.rentmanager.ui.roomDetails.payRentDialog.PayRentDialog;
import com.example.sumitlakra.rentmanager.ui.roomDetails.readingDialog.ReadingDialog;
import com.example.sumitlakra.rentmanager.ui.roomDetails.rentDetail.RentDetailDialog;
import com.example.sumitlakra.rentmanager.ui.showDetails.ShowDetailsActivity;

import android.widget.AdapterView.OnItemSelectedListener;

import java.util.Objects;

public class RoomDetailActivity extends BaseActivity implements RoomDetailMvpView,OnItemSelectedListener {

    private RoomDetailMvpPresenter<RoomDetailMvpView> mPresenter;
    private TextView name, totalMembers, rent, tvTitle, dueOn;
    private EditText comments;
    private static String mTitle;
    private String month;
    private int monthPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        mPresenter = new RoomDetailPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        name = findViewById(R.id.rd_tv_name_right);
        totalMembers = findViewById(R.id.rd_tv_member_right);
        rent = findViewById(R.id.rd_tv_rent_right);
        comments = findViewById(R.id.rd_ed_comments);
        tvTitle = findViewById(R.id.rd_tv_title);
        dueOn = findViewById(R.id.rd_tv_rent_due_right);

        mTitle = (Objects.requireNonNull(getIntent().getExtras())).getString("TITLE");
        mPresenter.setRoomTitle(mTitle);
        mPresenter.getRoomDetails(mTitle, "January");

        rent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if (motionEvent.getRawX() >= rent.getRight() -
                            rent.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()){
                        mPresenter.showRentDetails();
                        return true;
                    }
                }
                return false;
            }
        });

        Spinner monthSpinner = findViewById(R.id.rd_month_spinner);
        monthSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        Button calculateRent = findViewById(R.id.btn_calculate_rent);
        calculateRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.calculateRentClicked(mTitle, month, monthPosition);
            }
        });
        Button save = findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveComments(comments.getText().toString().trim(), mTitle, month);
            }
        });

        Button payRent = findViewById(R.id.btn_pay_rent);
        payRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.payRentClicked(mTitle, month, monthPosition);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.room_rent_history, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_details:
                Intent intent = new Intent(RoomDetailActivity.this, EditRoomDetailsActivity.class);
                intent.putExtra("ROOMNO", mTitle);
                startActivity(intent);
                return true;
            case R.id.room_rent_history:
                mPresenter.roomRentHistoryClicked();
                return true;
            case R.id.room_details:
                mPresenter.roomDetailsClicked(mTitle, month);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(String mTitle) {
        tvTitle.setText(mTitle);
    }

    @Override
    public void setName(String mName) {
        name.setText(mName);
    }

    @Override
    public void setTotalMembers(String members) {
        totalMembers.setText(members);
    }

    @Override
    public void setRent(String mRent) {
        rent.setText(mRent);
    }

    @Override
    public void setComments(String comment) {
        comments.setText(comment);
    }

    @Override
    public void setRentDue(String message) {
        dueOn.setText(message);
    }

    @Override
    public void showReadingDialog() {
        DialogFragment fragment = ReadingDialog.newInstance(mTitle, month);
        fragment.show(getSupportFragmentManager(),"ReadingDialog");
    }

    @Override
    public void setEmptyCommentError() {
        comments.requestFocus();
        comments.setError("Please enter some comment");
    }


    @Override
    public void showPayRentDialog() {
        DialogFragment fragment = PayRentDialog.newInstance(mTitle, month);
        fragment.show(getSupportFragmentManager(), "PayRentDialog");

    }

    @Override
    public void navigateToRentHistory() {
        Intent intent = new Intent(RoomDetailActivity.this, RentHistoryActivity.class);
        intent.putExtra("ROOMNO", mTitle);
        intent.putExtra("ROOMLIST", true);
        startActivity(intent);
    }

    @Override
    public void showRentDetailsDialog() {
        DialogFragment fragment = RentDetailDialog.newInstance(mTitle, month);
        fragment.show(getSupportFragmentManager(), "RentDetailDialog");
    }

    @Override
    public void navigateToShowDetailsActivity(String roomNo, String mMonth) {
        Intent intent1 = new Intent(RoomDetailActivity.this, ShowDetailsActivity.class);
        intent1.putExtra("ROOMNO", roomNo);
        intent1.putExtra("MONTH",mMonth);
        startActivity(intent1);
    }

    public void updateRentStatus(String balance, String message){
        rent.setText(balance);
        dueOn.setText(message);
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
        mPresenter.getRoomDetails(mTitle, month);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
