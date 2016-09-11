package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by nmadapati on 8/20/2016.
 */
public class StatisticDataUtil {
    Activity mActivity;
    Context mContext;
    private TextView mForehand ;
    private TextView mBackHand;
    private TextView mVolley;
    private  TextView mServe ;
    private  TextView mTotalBalls;
    StatisticData mStatisticData;
    private StatisticDBAdapter mStatisticDBAdapter;

     public StatisticDataUtil( Context context) {

        mContext = context;
        mStatisticDBAdapter = new StatisticDBAdapter(context);
        mStatisticDBAdapter = mStatisticDBAdapter.read();
    }

    public void Initialization() {
        mForehand = (TextView)((Activity)mContext).findViewById(R.id.txtValueForehandValue);
        mBackHand = (TextView)((Activity)mContext).findViewById(R.id.txtViewBackendValue);
        mServe = (TextView)((Activity)mContext).findViewById(R.id.txtViewServeValue);
        mVolley = (TextView)((Activity)mContext).findViewById(R.id.txtViewVolleyValue);
        mTotalBalls = (TextView)((Activity)mContext).findViewById(R.id.txtViewTotalBallValue);

    }

    private void  updateTextField() {
        mForehand.setText(Integer.toString(mStatisticData.getmForehand()));
        mBackHand.setText(Integer.toString(mStatisticData.getmBackhand()));
        mServe.setText(Integer.toString(mStatisticData.getmServe()));
        mVolley.setText(Integer.toString(mStatisticData.getmVolley()));
        mTotalBalls.setText(Integer.toString(mStatisticData.getmTotalBalls()));
    }

    public void updateUserStatistic(String user_name ){
        mStatisticData = mStatisticDBAdapter.getUserStatistics(user_name);
        updateTextField();
    }
    public void updateAllTournamentStatistic(){
        mStatisticData = mStatisticDBAdapter.getAllTournamentStatistics();
        updateTextField();
    }
    public void updatePracticeStatistic( ){
        mStatisticData = mStatisticDBAdapter.getPracticeStatistics("Practice");
        updateTextField();
    }
}
