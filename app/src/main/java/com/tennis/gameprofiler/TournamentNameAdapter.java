package com.tennis.gameprofiler;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nmadapati on 8/16/2016.
 */
public class TournamentNameAdapter extends ArrayAdapter<String> {
        private Context context;
        private List<String> mTournamentName = new ArrayList<String>();
        private static final String tag = "FoodItemArray";
        TextView mtournamentItemText;
        LayoutInflater inflter;

        public TournamentNameAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId,objects);
            this.context = context;
            this.mTournamentName = objects;
            inflter = (LayoutInflater.from(context));


        }
        public int getCount() {
            return this.mTournamentName.size();
        }
        public String getItem(int index) {
            return this.mTournamentName.get(index);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
// ROW INFLATION
                Log.d(tag, "Starting XML Row Inflation ... ");
                LayoutInflater inflater = (LayoutInflater) this.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.tournament_name_list_item, null);
                TextView names = (TextView) row.findViewById(R.id.tournamentItemList);
                names.setText(mTournamentName.get(position));
                Log.d(tag, "Successfully completed XML Row Inflation!");

            }
            return row;
        }

}



