package com.example.sumitlakra.rentmanager.ui.roomDetails.rentDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseDialog;

public class RentDetailDialog extends BaseDialog implements RentDetailMvpView {
    private static final String TITLE = "title";
    private static final String MONTH = "month";
    private static final String MONTH_POSITION = "position";
    private static String mTitle, mMonth;
    private static int monthPosition;
    private RentDetailMvpPresenter<RentDetailMvpView> mPresenter;
    private TextView tvElectricityBill, tvDialogTitle, tvWaterBill, tvBalance;

    public static RentDetailDialog newInstance(String roomNo, String month, int monthPosition){
        RentDetailDialog rentDetailDialog = new RentDetailDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, roomNo);
        bundle.putString(MONTH, month);
        bundle.putInt(MONTH_POSITION, monthPosition);
        rentDetailDialog.setArguments(bundle);
        return rentDetailDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mTitle = getArguments().getString(TITLE);
            mMonth = getArguments().getString(MONTH);
            monthPosition = getArguments().getInt(MONTH_POSITION);
        }

        mPresenter = new RentDetailPresenter<>(getBaseActivity().getAppDataManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rent_detail_dialog, container, false);
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        tvDialogTitle = view.findViewById(R.id.tv_rd_dialog_title);
        tvElectricityBill = view.findViewById(R.id.tv_electricity_bill_right);
        tvWaterBill = view.findViewById(R.id.tv_water_bill_right);
        tvBalance = view.findViewById(R.id.tv_balance_right);

        Button ok = view.findViewById(R.id.btn_rd_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissDialog();
            }
        });
        mPresenter.setTitle(mTitle, mMonth);
        mPresenter.getWaterBill(mTitle, mMonth);
        mPresenter.getElectricityBill(mTitle, mMonth);
        mPresenter.getBalance(mTitle, monthPosition);

    }

    @Override
    public void setDialogTitle(String dialogTitle) {
        tvDialogTitle.setText(dialogTitle);
    }

    @Override
    public void setBalance(String balance) {
        tvBalance.setText(balance);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog();
    }

    @Override
    public void setWaterBill(String waterBill) {
        tvWaterBill.setText(waterBill);
    }

    @Override
    public void setElectricityBill(String electricityBill) {
        tvElectricityBill.setText(electricityBill);
    }
}
