package com.tennis.gameprofiler;

import android.app.Application;

/**
 * Created by nmadapati on 7/20/2016.
 */
public class GameProfilerApp extends Application {
    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    private String mUserName ;
       public boolean isSignIndone() {
        return isSignIndone;
    }

    public void setIsSignIndone(boolean isSignIndone) {
        this.isSignIndone = isSignIndone;
    }

    private boolean isSignIndone = false;

    public static int getmExitFlag() {
        return mExitFlag;
    }

    public static void setmExitFlag(int mExitFlag) {
        GameProfilerApp.mExitFlag = mExitFlag;
    }

    private static int mExitFlag=0;
}
