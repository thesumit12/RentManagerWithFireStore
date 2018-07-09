package com.example.sumitlakra.rentmanager.ui.roomDetails.payRentDialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseDialog;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737Activity;
import com.example.sumitlakra.rentmanager.ui.roomDetails.RoomDetailActivity;


public class PayRentDialog extends BaseDialog implements PayRentDialogMvpView {

    private static final String TAG = "PayRentDialog";
    private static final String TITLE = "Pay Rent";
    private static final String MONTH = "month";

    private PayRentDialogMvpPresenter<PayRentDialogMvpView> mPresenter;

    private String roomNo;
    private String month;

    private EditText etRent;
    private RoomDetailActivity mActivity;

    public static PayRentDialog newInstance(String roomNo, String monthPassed){
        PayRentDialog fragment = new PayRentDialog();
        Bundle bundle = new Bundle();
        bundle.putString("ROOMNO",roomNo);
        bundle.putString(MONTH, monthPassed);
        fragment.setArguments(bundle);
        return fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            roomNo = getArguments().getString("ROOMNO");
            month = getArguments().getString(MONTH);
        }
        mPresenter = new PayRentDialogPresenter<>(getBaseActivity().getAppDataManager());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_rent_dialog, container, false);
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        Button submit, cancel;
        TextView title;
        submit = view.findViewById(R.id.btn_submit);
        cancel = view.findViewById(R.id.btn_cancel);
        title = view.findViewById(R.id.tv_pr_title);
        title.setText(TITLE);
        etRent = view.findViewById(R.id.et_pr_rent);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"month: "+month);
                mPresenter.onRentSubmitted(roomNo,etRent.getText().toString().trim(), month);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onCancelClicked();
            }
        });
    }

    @Override
    public void setRentEmptyError() {
        etRent.requestFocus();
        etRent.setError("Rent Empty");
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void refreshView(String balance, String message) {
        mActivity.updateRentStatus(balance, message);
    }
}
