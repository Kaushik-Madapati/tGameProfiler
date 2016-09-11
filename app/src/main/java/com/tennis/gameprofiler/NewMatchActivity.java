package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tennis.gameprofiler.GameProfilerBaseClass;
import com.tennis.gameprofiler.StatisticDBAdapter;

public class NewMatchActivity extends GameProfilerBaseClass {


    TextView txtForehandValue;
    TextView txtBackhandValue;
    TextView txtServeValue;
    TextView txtVolleyValue;
    TextView editTxtTotalBallValue;
    EditText editTextFinalScore;
    EditText editTextMatchName;
    EditText editTextComments;
    Button  btnSave;

    StatisticDBAdapter mStatisticDBAdapater;

    private Integer mForehandValue = 0;
    private Integer mBackhandValue = 0;
    private Integer mVolleyValue = 0;
    private Integer mServeValue = 0;
    private Integer mTotalBalls = 0;
    private String mTournament = null;
    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int mNoOfPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_new_match);

        mStatisticDBAdapater = new StatisticDBAdapter(this);

        String userName = mApp.getmUserName();
        this.setTitle(userName);

  //      onCreateAdView();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTournament= extras.getString(TournamentMainActivity.TOURNAMENT_NAME);
        }


        Button btnForehand = (Button) findViewById(R.id.btnForehandAdd);

        txtForehandValue = (TextView) findViewById(R.id.txtForehandValue);
        txtBackhandValue = (TextView) findViewById(R.id.txtViewBackendValue);
        txtServeValue = (TextView) findViewById(R.id.txtViewServeValue);
        txtVolleyValue = (TextView) findViewById(R.id.txtViewVolleyValue);
        editTxtTotalBallValue = (EditText)findViewById(R.id.txtViewTotalBallsValue);
        editTextMatchName  = (EditText)findViewById(R.id.editTextTournamentNewMatchName);
        editTextFinalScore  = (EditText)findViewById(R.id.editTexttournamentFinalScore);
        editTextComments  = (EditText)findViewById(R.id.editTextTournamentNewMatchComments);


        btnForehand.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnForehand();
            }
        });
        Button btnBackend = (Button) findViewById(R.id.btnBackendAdd);
        btnBackend.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnBackend();
            }
        });
        Button btnVolley = (Button) findViewById(R.id.btnVolleyAdd);
        btnVolley.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnVolley();
            }
        });
        Button btnServe = (Button) findViewById(R.id.btnServeAdd);
        btnServe.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnServe();
            }
        });
        Button btnTotalBalls = (Button) findViewById(R.id.btnTotalBallAdd);
        btnTotalBalls.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBtnTotalBalls();
            }
        });




        /// Match final score


        editTextMatchName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        editTextFinalScore.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        editTextComments.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    private void handleShakeEvent(int count) {
        mNoOfPoints = count;
    }

    public void onBtnForehand() {
        mForehandValue++;
        txtForehandValue.setText(mForehandValue.toString());
        mTotalBalls++;
        editTxtTotalBallValue.setText(mTotalBalls.toString());
        return;
    }

    public void onBtnBackend() {
        mBackhandValue++;
        txtBackhandValue.setText(mBackhandValue.toString());
        mTotalBalls++;
        editTxtTotalBallValue.setText(mTotalBalls.toString());
        return;
    }
    public void onBtnVolley() {
        mVolleyValue++;
        txtVolleyValue.setText(mVolleyValue.toString());
        mTotalBalls++;
        editTxtTotalBallValue.setText(mTotalBalls.toString());
        return;
    }
    public void onBtnServe() {
        mServeValue++;
        txtServeValue.setText(mServeValue.toString());
        mTotalBalls++;
        editTxtTotalBallValue.setText(mTotalBalls.toString());
        return;
    }

    public void onBtnTotalBalls() {

        mTotalBalls++;
        editTxtTotalBallValue.setText(mTotalBalls.toString());

        return;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mForehandValue = 0;
        mBackhandValue = 0;
        mVolleyValue = 0;
        mServeValue = 0;
        mTotalBalls = 0;
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        onBtnSave();

    }


    public void onBtnSave() {

        mStatisticDBAdapater = mStatisticDBAdapater.open();
        mStatisticDBAdapater.insertEntry( mApp.getmUserName(),
                    mTournament, 0,
                    mForehandValue,
                    mBackhandValue,
                    mServeValue,
                    mVolleyValue,
                    editTextFinalScore.getText().toString(),
                    mTotalBalls,
                    editTextMatchName.getText().toString(),
                    editTextComments.getText().toString());

        this.finish();

        return;
    }

}


