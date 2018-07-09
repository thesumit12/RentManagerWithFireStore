package com.example.sumitlakra.rentmanager.ui.plot737.dialog;

import android.app.Activity;
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
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.base.BaseDialog;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737Activity;


public class DeleteRoomDialog extends BaseDialog implements DeleteRoomDialogMvpView {

    private static final String TAG = "PayRentDialog";

    private DeleteRoomDialogMvpPresenter<DeleteRoomDialogMvpView> mPresenter;

    private String mTitle = "Test";

    private EditText roomNo;
    private Plot737Activity mActivity;

    public static DeleteRoomDialog newInstance(String title){
        DeleteRoomDialog fragment = new DeleteRoomDialog();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Plot737Activity){
            this.mActivity = (Plot737Activity) activity;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Plot737Activity){
            this.mActivity = (Plot737Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mTitle = getArguments().getString("TITLE");
        }
        mPresenter = new DeleteRoomDialogPresenter<>(getBaseActivity().getAppDataManager());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.room_dialog, container, false);
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        Button submit, cancel;
        TextView title;
        submit = view.findViewById(R.id.btn_submit);
        cancel = view.findViewById(R.id.btn_cancel);
        title = view.findViewById(R.id.tv_dialog_title);
        title.setText(mTitle);
        roomNo = view.findViewById(R.id.et_dialog_roomNo);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRoomNoSubmitted(roomNo.getText().toString().trim());
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
    public void setRoomEmptyError() {
        roomNo.setError("Please enter room number");
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void refreshView() {
        mActivity.getUpdatedList();
    }
}
