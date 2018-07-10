package com.example.sumitlakra.rentmanager.ui.showDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sumitlakra.rentmanager.R;

public class ShowDetailsActivity extends AppCompatActivity {

    private String mMonth, mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        if (getIntent().getExtras() != null){
            mTitle = getIntent().getStringExtra("ROOMNO");
            mMonth = getIntent().getStringExtra("MONTH");
        }
    }
}
