package com.tennis.gameprofiler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class PracticeMenuActivity extends GameProfilerBaseClass {

    Button mBtnSession ;
    Button mBtnMatch;

     StatisticDataUtil mStatisticDataUtil = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_main_menu);

        mBtnSession = (Button)findViewById(R.id.btnPracticeSession);
        mBtnMatch = (Button)findViewById(R.id.btnPracticeMatch);

        onCreateAdView();

        String userName = mApp.getmUserName();
        this.setTitle(userName);
        String summaryString = "Practice Performance ";
        SetStatTitle(summaryString);

        mStatisticDataUtil = new StatisticDataUtil(this);
        mStatisticDataUtil.Initialization();

        mBtnSession.setOnClickListener(
                new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnSession();

            }
        });

        mBtnMatch.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnMatch();

            }
        });

    }

    /**
     *    on Btn Session callback
     */
    public void onBtnSession() {
      Intent intent = new Intent(this, PracticeSessionSummaryActivity.class);
        intent.putExtra(TournamentMainActivity.TOURNAMENT_NAME, "Practice");
        startActivity(intent);
        return;

    }

    /**
     *   on Btn Match callback
     */
    public void onBtnMatch() {
        Intent intent = new Intent(this, NewMatchActivity.class);
        intent.putExtra(TournamentMainActivity.TOURNAMENT_NAME, "Practice");
        startActivity(intent);
        return;

    }



    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mStatisticDataUtil.updatePracticeStatistic();

    }
}
