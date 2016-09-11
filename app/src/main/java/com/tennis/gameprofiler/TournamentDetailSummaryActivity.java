package com.tennis.gameprofiler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tennis.gameprofiler.GameProfilerBaseClass;
import com.tennis.gameprofiler.R;
import com.tennis.gameprofiler.StatisticDBAdapter;
import com.tennis.gameprofiler.StatisticData;
import com.tennis.gameprofiler.TournamentMainActivity;


public class TournamentDetailSummaryActivity extends GameProfilerBaseClass {

    TextView mForehand ;
    TextView mBackHand;
    TextView mVolley;
    TextView mServe ;
    TextView mTotalBall;
    private Button mBtnClose = null ;
  //  MyTennisStatsApp mApp;
   StatisticData mStatisticData;
    String  mTournamentName = null;
    private int mDBID = 0;

    private StatisticDBAdapter mStatisticDBAdapter;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_detail_summary);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           mDBID= extras.getInt(TournamentMainActivity.DB_ID);
        }

      //  mApp = (MyTennisStatsApp)getApplicationContext();

        mStatisticDBAdapter = new StatisticDBAdapter(this);
        mStatisticDBAdapter = mStatisticDBAdapter.read();

        onCreateAdView();

        String userName = mApp.getmUserName();
        this.setTitle(userName);


        mForehand = (TextView)findViewById(R.id.txtValueForehandValue);
        mBackHand = (TextView)findViewById(R.id.txtViewBackendValue);
        mServe = (TextView)findViewById(R.id.txtViewServeValue);
        mVolley = (TextView)findViewById(R.id.txtViewVolleyValue);
        mTotalBall = (TextView)findViewById(R.id.txtViewTotalBallValue);

   }

    @Override
    /**
     * on Resume activity
     */
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mStatisticData = mStatisticDBAdapter.getDetailStatistic(mDBID);
        mForehand.setText(Integer.toString(mStatisticData.getmForehand()));
        mBackHand.setText(Integer.toString(mStatisticData.getmBackhand()));
        mServe.setText(Integer.toString(mStatisticData.getmServe()));
        mVolley.setText(Integer.toString(mStatisticData.getmVolley()));
        mTotalBall.setText(Integer.toString(mStatisticData.getmTotalBalls()));
    }

    /**
     *   onPause
     */
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    private void onBtnClose() {
        finish();

    }
}
