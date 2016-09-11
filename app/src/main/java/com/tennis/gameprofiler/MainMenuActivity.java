package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainMenuActivity extends GameProfilerBaseClass {
    private Button btnPractice;
    private Button btnTournament;
    private StatisticDataUtil mStatisticDataUtil;
    private String mUserName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        mStatisticDataUtil = new StatisticDataUtil(this);

        mUserName = mApp.getmUserName();
        this.setTitle(mUserName);

        onCreateAdView();

        String summaryString = "Overall Performance ";
        SetStatTitle(summaryString);

        mStatisticDataUtil.Initialization();

        btnPractice = (Button) findViewById(R.id.btnMainPractice);
        btnTournament = (Button) findViewById(R.id.btnMainTournament);


        btnPractice.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnPractice();

            }
        });

        btnTournament.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnTournament();

            }
        });
    }
        /**
         *  On Practice Btn press
         */

    public void onBtnPractice() {

        Intent intent = new Intent(this, PracticeMenuActivity.class);

        startActivity(intent);

        return;

    }

    /**
     * On Tournament main activity
     */

    public void onBtnTournament() {
        Intent intent = new Intent(this, TournamentMainActivity.class);

        startActivity(intent);
        return;

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mStatisticDataUtil.updateUserStatistic(mUserName);



    }
}

