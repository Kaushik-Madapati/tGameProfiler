package com.tennis.gameprofiler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by nmadapati on 8/27/2016.
 *
 */


import com.tennis.gameprofiler.TournamentScore;

import java.util.ArrayList;
import java.util.List;

public class TournamentScoreAdapter extends ArrayAdapter<TournamentScore> {

    private Context context;
    private List<TournamentScore> tournamentScores = new ArrayList<TournamentScore>();
    private static final String tag = "FoodItemArray";
    TextView mTournamentScoreItemTxt;

    public TournamentScoreAdapter(Context context, int textViewResourceId, List<TournamentScore> objects) {
        super(context, textViewResourceId,objects);
        this.context = context;
        this.tournamentScores = objects;


    }
    public int getCount() {
        return this.tournamentScores.size();
    }
    public TournamentScore getItem(int index) {
        return this.tournamentScores.get(index);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
// ROW INFLATION
            Log.d(tag, "Starting XML Row Inflation ... ");
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.tournament_score_list_item, parent, false);
            Log.d(tag, "Successfully completed XML Row Inflation!");
        }
        TournamentScore tournamentScoreItem = getItem(position);
        mTournamentScoreItemTxt = (TextView) row.findViewById(R.id.tournament_score_item);
        if(tournamentScoreItem.getmScore().isEmpty())
            tournamentScoreItem.setmScore("No Score");

        mTournamentScoreItemTxt.setText(tournamentScoreItem.getmScore());

        return row;
    }

}
