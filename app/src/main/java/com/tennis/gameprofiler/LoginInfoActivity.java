package com.tennis.gameprofiler;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class LoginInfoActivity extends GameProfilerBaseClass {

   private TextView mUserNameTView ;
   private TextView mPasswdTView;
   private  String mUserName;
   private Button mBtnClose;
   private  String mPassWd;
    private RadioButton mGuestRadioBtn;

    private UserDBAdapter userDBAdapter;



    private Button mSignInBtn ;
    private Button mSignUpBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_info);

        userDBAdapter =new UserDBAdapter(this);
        userDBAdapter =userDBAdapter.open();



        String userName = mApp.getmUserName();
        this.setTitle( userName);


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
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onSignUp();

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
            Toast.makeText(LoginInfoActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
            mApp.setmUserName(mUserName);
            userPreference.writeInstanceStateUserName(this, mUserName);
            userPreference.writeInstanceState(this, true);
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);


        }
        else
        {

            Toast.makeText(LoginInfoActivity.this, "User Name and Does Not Matches", Toast.LENGTH_LONG).show();
        }

    }


    private void onGuestRadioBtn () {


        userDBAdapter.insertEntry("Guest", "passwd", null);
        mApp.setmUserName("Guest");
        userPreference.writeInstanceStateUserName(this,"Guest");
        this.finish();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mSignInBtn.setEnabled(true);
        mSignUpBtn.setEnabled(true);
        mUserNameTView.setEnabled(true);
        mPasswdTView.setEnabled(true);

    }


 }

