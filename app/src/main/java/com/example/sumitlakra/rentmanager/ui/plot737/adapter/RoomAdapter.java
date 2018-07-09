package com.example.sumitlakra.rentmanager.ui.plot737.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737MvpPresenter;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737MvpView;
import com.example.sumitlakra.rentmanager.ui.plot737.RoomRowView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyRoomViewHolder>{

    private final Plot737MvpPresenter<Plot737MvpView> mPresenter;

    public RoomAdapter(Plot737MvpPresenter<Plot737MvpView> presenter){
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public MyRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRoomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoomViewHolder holder, int position) {
        mPresenter.onBindRoomRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getRoomRowsCount();
    }

    class MyRoomViewHolder extends RecyclerView.ViewHolder implements RoomRowView,View.OnClickListener {

        ImageView image;
        TextView roomNumber;

        public MyRoomViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ib_home_button);
            roomNumber = itemView.findViewById(R.id.tv_room_no);
            image.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setImage(int imageId) {
            image.setImageResource(imageId);
        }

        @Override
        public void setTitle(String name) {
            roomNumber.setText(name);
        }

        @Override
        public void onClick(View v) {
            if (mPresenter != null){
                mPresenter.onItemInteraction(getAdapterPosition());
            }
        }
    }
}
