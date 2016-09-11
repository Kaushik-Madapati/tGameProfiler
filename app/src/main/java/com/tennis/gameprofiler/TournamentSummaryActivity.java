package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tennis.gameprofiler.GameProfilerBaseClass;
import com.tennis.gameprofiler.StatisticDBAdapter;
import com.tennis.gameprofiler.TournamentScore;

import java.util.ArrayList;

public class TournamentSummaryActivity extends GameProfilerBaseClass {

    Button btnNewMatch;
    String mTournamentName = null;
    private StatisticDBAdapter mStatisticDBAdapter;
    private ArrayList<TextView> mTextViewList = null;
    private ArrayList<TournamentScore> mTournamentScoreList = null;
    private int mNoOfRec = 0;
    private TextView mTextViewTournamentName;
    TournamentScoreAdapter itemListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_summary_layout);

        mStatisticDBAdapter = new StatisticDBAdapter(this);
        mStatisticDBAdapter = mStatisticDBAdapter.read();
        Bundle extras = getIntent().getExtras();

        String userName = mApp.getmUserName();
        this.setTitle(userName);

        onCreateAdView();

        if (extras != null) {
            mTournamentName= extras.getString(TournamentMainActivity.TOURNAMENT_NAME);
         }

        mTextViewTournamentName  = (TextView)findViewById(R.id.textViewTournaentName) ;


        btnNewMatch = (Button)findViewById(R.id.btnTournamentNewMatch);
        btnNewMatch.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnNewMatch();

            }
        });
        createTournamentScoreList();
      }

    /**
     *
     * @param itemId
     */
    public void onItemSelection(int itemId) {

        Intent intent = new Intent(this, TournamentDetailSummaryActivity.class);
        intent.putExtra(TournamentMainActivity.DB_ID, itemId);
        startActivity(intent);

        return;
    }

    /**
     *  when New Match selected
     */
    public void onBtnNewMatch() {
        Intent intent = new Intent(this, NewMatchActivity.class);
        intent.putExtra(TournamentMainActivity.TOURNAMENT_NAME, mTournamentName);

        startActivity(intent);

        return;
    }

    /**
     *  OnResume
     */

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mTournamentScoreList = mStatisticDBAdapter.getAllScoresForGivenTournament(mTournamentName);
        itemListAdapter.notifyDataSetChanged();

     }

    /**
     *  On puase
     */
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    /**
     * create tournament score list
     */
    private void createTournamentScoreList() {
         mNoOfRec = mStatisticDBAdapter.getTotalRecInTournament(mTournamentName);

        if(mNoOfRec == 0) {
            String msg = "No score found in " + "\n" + mTournamentName;
            mTextViewTournamentName.setText(msg);
        }
        else
            mTextViewTournamentName.setText(mTournamentName);
        mTournamentScoreList = mStatisticDBAdapter.getAllScoresForGivenTournament(mTournamentName);

        ListView mTournamentScoreListView = (ListView) findViewById(R.id.list_tournament_scores);

        itemListAdapter = new TournamentScoreAdapter(this, R.layout.tournament_score_list_item,mTournamentScoreList );
        mTournamentScoreListView.setAdapter(itemListAdapter);
        mTournamentScoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long duration) {

          //      Toast.makeText(getApplicationContext(), mItemSelected, Toast.LENGTH_SHORT).show();
                onItemSelection(itemListAdapter.getItem(position).getmID());
            }
        });




    }
    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
