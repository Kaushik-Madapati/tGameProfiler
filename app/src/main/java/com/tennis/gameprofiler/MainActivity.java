package com.tennis.gameprofiler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends GameProfilerBaseClass {
    // Remove the below line after defining your own ad unit ID.


    private Button btnPractice;
    private Button btnTournament;
    //   private UserPreference userPreference;
    private TextView mUserNameTView ;
    private TextView mPasswdTView;
    private  String mUserName;
    private Button mBtnClose;
    private  String mPassWd;
    private RadioButton mGuestRadioBtn;

    private UserDBAdapter userDBAdapter;

    // private TennisPerformanceProfileApp mApp;

    private Button mSignInBtn ;
    private Button mSignUpBtn;
    private TextView mForehand ;
    private TextView mBackHand;
    private TextView mVolley;
    private TextView mServe;
    private StatisticDBAdapter mStatisticDBAdapter;
    StatisticData mStatisticData;
    private StatisticDataUtil mStatisticDataUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreateAdView();
        userDBAdapter =new UserDBAdapter(this);
        userDBAdapter =userDBAdapter.open();
        mForehand = (TextView)findViewById(R.id.txtValueForehandValue);
        mBackHand = (TextView)findViewById(R.id.txtViewBackendValue);
        mServe = (TextView)findViewById(R.id.txtViewServeValue);
        mVolley = (TextView)findViewById(R.id.txtViewVolleyValue);
        mStatisticDBAdapter = new StatisticDBAdapter(this);
        mStatisticDBAdapter = mStatisticDBAdapter.read();
        mStatisticDataUtil = new StatisticDataUtil(this);


        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        if(mApp.getmExitFlag() == 1)
        {
            this.finish();
            mApp.setmExitFlag(0);
        }

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.

    }
    /**
     *
     */

    private void onSignUp(){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        return;

    }

    /**
     *    Sign In Btn function
     */

    private void onSignIn(){

        mUserName = mUserNameTView.getText().toString();
        mPassWd = mPasswdTView.getText().toString();
        String storedPassword= userDBAdapter.getSinlgeEntry(mUserName);

        if(mPassWd.equals(storedPassword))
        {
            userPreference.writeInstanceState(this, true);
            userPreference.writeInstanceStateUserName(this,mUserName);
            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
            mApp.setmUserName(mUserName);
            Intent intent = new Intent(this, MainMenuActivity.class);

            startActivity(intent);

            return;


        }
        else
        {

            Toast.makeText(MainActivity.this, "User Name and Does Not Matches", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



    /**
     * on Btn Close
     */
    private void onBtnClose() {
        finish();

    }

    private void onGuestRadioBtn () {


        userDBAdapter.insertEntry("Guest", "passwd", null);
        Intent intent = new Intent(this, MainMenuActivity.class);
        mApp.setmUserName("Guest");
        userPreference.writeInstanceStateUserName(this,"Guest");

        startActivity(intent);

    }

    /**
     *  On Practice Btn press
     */

    public void onBtnPractice() {

        Intent intent = new Intent(this, PracticeMenuActivity.class);

        startActivity(intent);

        return;

    }

    /**
     * On Tournament main activity
     */

    public void onBtnTournament() {
        Intent intent = new Intent(this, TournamentMainActivity.class);

        startActivity(intent);
        return;

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if(!userPreference.readInstanceState(this, UserSettingType.FIRST_TIME_START_UP )){


            AdView adView = (AdView) findViewById(R.id.adView);
            adView.setVisibility(View.GONE);
            LinearLayout loginLayout = (LinearLayout)findViewById(R.id.layoutLogin);
            loginLayout.setVisibility(View.VISIBLE);
            LinearLayout mainMenuLayout = (LinearLayout)findViewById(R.id.layoutMainMenu);
            mainMenuLayout.setVisibility(View.GONE);

            userDBAdapter =new UserDBAdapter(this);
            userDBAdapter =userDBAdapter.open();

            mApp =  (GameProfilerApp)getApplicationContext();

            mSignInBtn = (Button)findViewById(R.id.buttonSignIn);
            mSignUpBtn  = (Button)findViewById(R.id.buttonSignUp);

            mUserNameTView = (TextView)findViewById(R.id.editTextUserNameToLogin);
            mPasswdTView = (TextView)findViewById(R.id.editTextPasswordToLogin);
            mGuestRadioBtn = (RadioButton)findViewById(R.id.radioButtonGuestLogin);

            mSignUpBtn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onSignUp();
                }
            });


            mSignInBtn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onSignIn();

                }
            });


            mGuestRadioBtn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onGuestRadioBtn();

                }
            });


        }
        else {

            userPreference.readInstanceStateUserName(this);

            String userName = userPreference.getmUserName();

            mApp.setmUserName(userName);
            this.setTitle(userName);
            String summaryString = "Overall Performance ";
            SetStatTitle(summaryString);
            AdView adView = (AdView) findViewById(R.id.adView);
            adView.setVisibility(View.VISIBLE);
            LinearLayout loginLayout = (LinearLayout)findViewById(R.id.layoutLogin);
            loginLayout.setVisibility(View.GONE);
            LinearLayout mainMenuLayout = (LinearLayout)findViewById(R.id.layoutMainMenu);
            mainMenuLayout.setVisibility(View.VISIBLE);



            btnPractice = (Button) findViewById(R.id.btnMainPractice);
            btnTournament = (Button) findViewById(R.id.btnMainTournament);

            mStatisticDataUtil.Initialization();
            mStatisticDataUtil.updateUserStatistic(userName);


            btnPractice.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onBtnPractice();

                }
            });

            btnTournament.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onBtnTournament();

                }
            });

            mStatisticDataUtil.updateUserStatistic(userName);
        }


    }





}
