package com.tennis.gameprofiler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticeNewSessionActivity extends GameProfilerBaseClass {

    EditText txtForehandValue;
    EditText txtBackhandValue;
    EditText txtServeValue;
    EditText txtVolleyValue;
    EditText txtTotalBallsValue;
    EditText editTextMatchName;
    EditText editTextComments;
    Button  btnSave;
    boolean mSaveFlag = false ;

    StatisticDBAdapter mStatisticDBAdapater;

    private Integer mForehandValue = 0;
    private Integer mBackhandValue = 0;
    private Integer mVolleyValue = 0;
    private Integer mServeValue = 0;
    private Integer mTotalBalls = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_session);
        mStatisticDBAdapater = new StatisticDBAdapter(this);



        String userName = mApp.getmUserName();
        this.setTitle(userName);

        onCreateAdView();

        editTextMatchName = (EditText)findViewById(R.id.editTextSessionName);
        editTextComments = (EditText)findViewById(R.id.editTextSessionComments);


        Button btnForehand = (Button) findViewById(R.id.btnForehandAdd);

        txtForehandValue = (EditText) findViewById(R.id.txtForehandValue);
        txtBackhandValue = (EditText) findViewById(R.id.txtViewBackendValue);
        txtServeValue = (EditText) findViewById(R.id.txtViewServeValue);
        txtVolleyValue = (EditText) findViewById(R.id.txtViewVolleyValue);
        txtTotalBallsValue = (EditText) findViewById(R.id.txtViewTotalBallsValue);


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
    }

    public void onBtnForehand() {
        mForehandValue++;
        txtForehandValue.setText(mForehandValue.toString());
        mTotalBalls++;
        txtTotalBallsValue.setText(mTotalBalls.toString());
        mSaveFlag = false;
        return;
    }

    public void onBtnBackend() {
        mBackhandValue++;
        txtBackhandValue.setText(mBackhandValue.toString());
        mTotalBalls++;
        txtTotalBallsValue.setText(mTotalBalls.toString());
        mSaveFlag = false;
        return;
    }
    public void onBtnVolley() {
        mVolleyValue++;
        txtVolleyValue.setText(mVolleyValue.toString());
        mTotalBalls++;
        txtTotalBallsValue.setText(mTotalBalls.toString());
        mSaveFlag = false;
        return;
    }
    public void onBtnServe() {
        mServeValue++;
        txtServeValue.setText(mServeValue.toString());
        mTotalBalls++;
        txtTotalBallsValue.setText(mTotalBalls.toString());
        mSaveFlag = false;
        return;
    }

    public void onBtnTotalBalls() {
        mTotalBalls++;
        txtTotalBallsValue.setText(mTotalBalls.toString());
        mSaveFlag = false;
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
       if(!mSaveFlag)
        onBtnSave();

    }


    public void onBtnSave() {
        mStatisticDBAdapater = mStatisticDBAdapater.open();

        mStatisticDBAdapater.insertEntry(mApp.getmUserName(),
                "Practice", 0,
                mForehandValue,
                mBackhandValue,
                mServeValue,
                mVolleyValue,
                null,
                mTotalBalls,
                editTextMatchName.getText().toString(),
                editTextComments.getText().toString());

        mSaveFlag = true;

        this.finish();

        return;
    }
}
