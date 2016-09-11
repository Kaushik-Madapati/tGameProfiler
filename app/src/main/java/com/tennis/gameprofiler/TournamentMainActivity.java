package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;



import java.util.ArrayList;
import java.util.List;

public class TournamentMainActivity extends GameProfilerBaseClass {

    TournamentDBAdapter tournamentDBAdapter;
    ArrayList<String> mTournamentList;
    TournamentNameAdapter mDataAdapter;
    static final String TOURNAMENT_NAME = "Tournament Name";
    static final String DB_ID = "Database ID";
    private Spinner mSpinnerTournamentName = null;
    private boolean initializedView = false;

     StatisticDataUtil mStatisticDataUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_main_menu);

        tournamentDBAdapter = new TournamentDBAdapter(this);
        tournamentDBAdapter = tournamentDBAdapter.open();

        mSpinnerTournamentName = (Spinner) findViewById(R.id.spinnerTournamentName);
        Button btnNewTournament = (Button) findViewById(R.id.btnNewTournament);

        mStatisticDataUtil = new StatisticDataUtil(this);

        onCreateAdView();

        String summaryString = "Tournament Performance";
        SetStatTitle(summaryString);

        mTournamentList = new ArrayList<String>();

         mDataAdapter = new TournamentNameAdapter(this, R.layout.tournament_name_list_item, mTournamentList);
       // mDataAdapter.setDropDownViewResource(R.tou);

        mSpinnerTournamentName.setAdapter(mDataAdapter);

        mSpinnerTournamentName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected (AdapterView < ? > parentView, View selectedItemView,
                int position, long id){

                if (initializedView == false)
                    initializedView = true;
                else {
                    Intent intent = new Intent(TournamentMainActivity.this, TournamentSummaryActivity.class);
                    intent.putExtra(TOURNAMENT_NAME, mDataAdapter.getItem(position));
                    TournamentMainActivity.this.startActivity(intent);
                }

            }
            public void onNothingSelected(AdapterView<?> parentView) {
                // To do ...
            }

        });
        btnNewTournament.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onNewTournament();

            }
        });
    }

    /**
     *  On New Tournament BTn press
     */

    public void onNewTournament() {

        Intent intent = new Intent(this, NewTournamentActivity.class);
        startActivity(intent);
        return;


    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mStatisticDataUtil.Initialization();
        mStatisticDataUtil.updateAllTournamentStatistic();

        mDataAdapter.clear();
       tournamentDBAdapter.getAllTournaments(mTournamentList);

        mDataAdapter.notifyDataSetChanged();
        if(mTournamentList.size() == 0)
            mSpinnerTournamentName.setEnabled(false);
        else {
            mSpinnerTournamentName.invalidate();
            mSpinnerTournamentName.setEnabled(true);
        }




    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mDataAdapter.clear();
        initializedView = false;

    }

}
