package com.tennis.gameprofiler;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nmadapati on 7/23/2016.
 */

enum  UserSettingType {FIRST_TIME_START_UP, USER_NAME}

public class UserPreference {
    /**
     * The name of a properties file that stores the position and
     * selection when the activity is not loaded.
     */
    public static final String PREFERENCES_FILE = "UserPrefs";

    public boolean ismFirstTimeStart() {
        return mFirstTimeStart;
    }

    public void setmFirstTimeStart(boolean mFirstTimeStart) {
        this.mFirstTimeStart = mFirstTimeStart;
    }

    private boolean mFirstTimeStart = false;

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    private String mUserName;




    /* Write the application's current state to a properties repository.
            * @param c - The Activity's Context
            *
            */
    public boolean writeInstanceState(Context c, boolean firstTime) {

        /*
         * Get the SharedPreferences object for this application
         */

        SharedPreferences p =
                c.getSharedPreferences(UserPreference.PREFERENCES_FILE,c.MODE_PRIVATE);
        /*
         * Get the editor for this object. The editor interface abstracts the implementation of
         * updating the SharedPreferences object.
         */

        SharedPreferences.Editor e = p.edit();

        e.putBoolean(UserDataInfo.FIRST_TIME_START_UP_KEY, firstTime);;

        /*
         * Commit the changes. Return the result of the commit. The commit fails if Android
         * failed to commit the changes to persistent storage.
         */


        return (e.commit());

    }

    public boolean writeInstanceStateUserName(Context c, String userName) {

        /*
         * Get the SharedPreferences object for this application
         */

        SharedPreferences p =
                c.getSharedPreferences(UserPreference.PREFERENCES_FILE,c.MODE_PRIVATE);
        /*
         * Get the editor for this object. The editor interface abstracts the implementation of
         * updating the SharedPreferences object.
         */

        SharedPreferences.Editor e = p.edit();

        e.putString(UserDataInfo.USER_NAME_KEY, userName);;

        /*
         * Commit the changes. Return the result of the commit. The commit fails if Android
         * failed to commit the changes to persistent storage.
         */


        return (e.commit());

    }


    class UserDataInfo
    {

        /**
         * The key or label for "position" in the preferences file
         */
        public static final String FIRST_TIME_START_UP_KEY = "First Time Start UP";

        /**
         * The key or label for "position" in the preferences file
         */
        public static final String USER_NAME_KEY = "User name";


    }
    /**
     * Read the previous state of the spinner from the preferences file
     * @param c - The Activity's Context
     */
    public boolean readInstanceState(Context c, UserSettingType type) {

        boolean result = true;

        /*
         * The preferences are stored in a SharedPreferences file. The abstract implementation of
         * SharedPreferences is a "file" containing a hashmap. All instances of an application
         * share the same instance of this file, which means that all instances of an application
         * share the same preference settings.
         */

        /*
         * Get the SharedPreferences object for this application
         */

        SharedPreferences p = c.getSharedPreferences(PREFERENCES_FILE, c.MODE_PRIVATE);
        /*
         * Get the position and value of the spinner from the file, or a default value if the
         * key-value pair does not exist.
         *
         */

        switch(type)
        {

            case FIRST_TIME_START_UP:
            {

                this.mFirstTimeStart = p.getBoolean(UserDataInfo.FIRST_TIME_START_UP_KEY, false);
                result = this.mFirstTimeStart;
                break;
            }


        }

        return result;

    }
    /**
     * Read the previous state of the spinner from the preferences file
     * @param c - The Activity's Context
     */
    public boolean readInstanceStateUserName(Context c) {

        boolean result = false;

        /*
         * The preferences are stored in a SharedPreferences file. The abstract implementation of
         * SharedPreferences is a "file" containing a hashmap. All instances of an application
         * share the same instance of this file, which means that all instances of an application
         * share the same preference settings.
         */

        /*
         * Get the SharedPreferences object for this application
         */

        SharedPreferences p = c.getSharedPreferences(PREFERENCES_FILE, c.MODE_PRIVATE);
        /*
         * Get the position and value of the spinner from the file, or a default value if the
         * key-value pair does not exist.
         *
         */


        this.mUserName = p.getString(UserDataInfo.USER_NAME_KEY, null);
        result = p.contains(UserDataInfo.USER_NAME_KEY);
        return result;

    }
}
