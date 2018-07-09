package com.example.sumitlakra.rentmanager.ui.roomDetails.readingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseDialog;
import com.example.sumitlakra.rentmanager.ui.roomDetails.RoomDetailActivity;

import java.util.Objects;

public class ReadingDialog extends BaseDialog implements ReadingDialogMvpView{

    private static final String TAG = "ReadingDialog";
    private static final String TITLE = "title";
    private static final String MONTH = "month";
    private static String mTitle;
    private ReadingDialogMvpPresenter<ReadingDialogMvpView> mPresenter;
    private TextView title, tv_prevMainReading, tv_prevRoomReading;
    private EditText mainReading, roomReading;
    private RoomDetailActivity mActivity;
    private static String month;

    public static ReadingDialog newInstance(String title, String month){
        ReadingDialog fragment = new ReadingDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MONTH, month);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mTitle = getArguments().getString(TITLE);
            month = getArguments().getString(MONTH);
        }
        mPresenter = new ReadingDialogPresenter<>(getBaseActivity().getAppDataManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reading_dialog, container, false);
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        title = view.findViewById(R.id.tv_rd_dialog_title);
        tv_prevMainReading = view.findViewById(R.id.tv_prev_main_reading_right);
        tv_prevRoomReading = view.findViewById(R.id.tv_prev_room_reading_right);
        mainReading = view.findViewById(R.id.et_rd_meter_reading);
        roomReading = view.findViewById(R.id.et_rd_room_reading);
        Button btnSubmit = view.findViewById(R.id.btn_rd_submit);
        Button btnCancel = view.findViewById(R.id.btn_rd_cancel);

        mPresenter.setUpTitle();
        mPresenter.getPreviousReading(mTitle, month);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submitClicked(mainReading.getText().toString().trim(),
                        roomReading.getText().toString(), mTitle, month);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cancelClicked();
            }
        });
    }


    @Override
    public void dismissDialog() {
        super.dismissDialog();
    }

    @Override
    public void setMainReadingEmptyError() {
        mainReading.requestFocus();
        mainReading.setError("Main Reading Empty!!");
    }

    @Override
    public void setRoomReadingEmptyError() {
        roomReading.requestFocus();
        roomReading.setError("Room Reading Empty!!");
    }

    @Override
    public void setWrongMainReadingError() {
        mainReading.requestFocus();
        mainReading.setError("Current reading can't be less than last reading");
    }

    @Override
    public void setWrongRoomReadingError() {
        roomReading.requestFocus();
        roomReading.setError("Current reading can't be less than last reading");
    }

    @Override
    public void setTitle() {
        title.setText(mTitle);
    }

    @Override
    public void refreshView(String totalRent, String message) {
        mActivity.updateRentStatus(totalRent,message);
    }

    @Override
    public void setPreviousReadings(int mainReading, int roomReading) {
        tv_prevRoomReading.setText(String.valueOf(roomReading));
        tv_prevMainReading.setText(String.valueOf(mainReading));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof RoomDetailActivity){
            this.mActivity = (RoomDetailActivity) activity;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomDetailActivity){
            this.mActivity = (RoomDetailActivity) context;
        }
    }
}
