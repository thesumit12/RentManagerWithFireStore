package com.example.sumitlakra.rentmanager.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sumitlakra.rentmanager.R;
import com.example.sumitlakra.rentmanager.ui.base.BaseActivity;
import com.example.sumitlakra.rentmanager.ui.plot737.Plot737Activity;

public class MainActivity extends BaseActivity implements MainMvpView{

    private MainMvpPresenter<MainMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        TextView plot1 = findViewById(R.id.tv_plot1);
        plot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.plot1Clicked();
            }
        });

        TextView plot2 = findViewById(R.id.tv_plot2);
        plot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.plot2Clicked();
            }
        });
    }

    @Override
    public void navigateToPlot1() {
        Intent intent = new Intent(MainActivity.this, Plot737Activity.class);
        intent.putExtra("NAME","Plot 737");
        startActivity(intent);
    }

    @Override
    public void navigateToPlot2() {
        showMessage("Plot 2 clicked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
