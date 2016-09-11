package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticeSessionSummaryActivity extends GameProfilerBaseClass {

    TextView mForehand ;
    TextView mBackHand;
    TextView mVolley;
    TextView mServe ;
    TextView mTotalBalls;
    Button mBtnNewSession;

    private StatisticDBAdapter mStatisticDBAdapter = null;


   StatisticData  mStatisticData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_session_summary);
        mStatisticDBAdapter = new StatisticDBAdapter(this);
        mStatisticDBAdapter = mStatisticDBAdapter.open();


        String userName = mApp.getmUserName();
        this.setTitle(userName);

        String summaryString = "Practice Session  ";
        SetStatTitle(summaryString);

        onCreateAdView();
        mForehand = (TextView) findViewById(R.id.txtValueForehandValue);
        mBackHand = (TextView) findViewById(R.id.txtViewBackendValue);
        mServe = (TextView) findViewById(R.id.txtViewVolleyValue);
        mVolley = (TextView) findViewById(R.id.txtViewServeValue);
        mTotalBalls = (TextView) findViewById(R.id.txtViewTotalBallValue);




        mBtnNewSession = (Button) findViewById(R.id.btnNewSession);
        mBtnNewSession.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnNewSession();

            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mStatisticData  = mStatisticDBAdapter.getPracticeStatistics("Practice");

        if(mStatisticData == null)
            return;

        Integer forehand = mStatisticData.getmForehand();
        mForehand.setText(Integer.toString(mStatisticData.getmForehand()));
        mBackHand.setText(String.valueOf(mStatisticData.getmBackhand()));
        mServe.setText(String.valueOf(mStatisticData.getmServe()));
        mVolley.setText(String.valueOf(mStatisticData.getmVolley()));
        mTotalBalls.setText(String.valueOf(mStatisticData.getmTotalBalls()));
     }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }
     public void onBtnNewSession(){
         Intent intent = new Intent(this, PracticeNewSessionActivity.class);
         startActivity(intent);
         return;
          }
    }
