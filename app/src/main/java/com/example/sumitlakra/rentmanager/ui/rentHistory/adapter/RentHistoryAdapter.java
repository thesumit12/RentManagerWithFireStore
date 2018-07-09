package com.example.sumitlakra.rentmanager.ui.rentHistory.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.rentHistory.RentHistoryMvpPresenter;
import com.example.sumitlakra.rentmanager.ui.rentHistory.RentHistoryMvpView;
import com.example.sumitlakra.rentmanager.ui.rentHistory.RentHistoryRowView;

public class RentHistoryAdapter extends RecyclerView.Adapter<RentHistoryAdapter.RentHistoryViewHolder> {

    private final RentHistoryMvpPresenter<RentHistoryMvpView> mPresenter;

    public RentHistoryAdapter(RentHistoryMvpPresenter<RentHistoryMvpView> presenter){
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public RentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RentHistoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rent_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RentHistoryViewHolder holder, int position) {
        mPresenter.onBindRoomRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getRoomRowsCount();
    }

    class RentHistoryViewHolder extends RecyclerView.ViewHolder implements RentHistoryRowView{
        TextView mRoomNo, mMonth, mRentPaid, mBalance, mTimeStamp;

        public RentHistoryViewHolder(View itemView) {
            super(itemView);
            mRoomNo = itemView.findViewById(R.id.tv_rr_roomNo);
            mMonth = itemView.findViewById(R.id.tv_rr_month);
            mRentPaid = itemView.findViewById(R.id.tv_rr_rent_paid);
            mBalance = itemView.findViewById(R.id.tv_rr_balance);
            mTimeStamp = itemView.findViewById(R.id.tv_rr_time_stamp);
        }

        @Override
        public void setRoomNo(String roomNo) {
            mRoomNo.setText(roomNo);
        }

        @Override
        public void setMonth(String month) {
            mMonth.setText(month);
        }

        @Override
        public void setRentPaid(String rentPaid) {
            mRentPaid.setText(rentPaid);
        }

        @Override
        public void setBalance(String balance) {
            mBalance.setText(balance);
        }

        @Override
        public void setTimeStamp(String timeStamp) {
            mTimeStamp.setText(timeStamp);
        }
    }
}
