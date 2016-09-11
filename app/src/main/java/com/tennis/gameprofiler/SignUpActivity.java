package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tennis.gameprofiler.GameProfilerBaseClass;
import com.tennis.gameprofiler.R;
import com.tennis.gameprofiler.UserDBAdapter;


public class SignUpActivity extends GameProfilerBaseClass {

    private EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateNext;
    private EditText mEditTextEmailTxt;

    private UserDBAdapter mUserDBAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

            // Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        mEditTextEmailTxt = (EditText)findViewById(R.id.editTextEmail);



        btnCreateNext=(Button)findViewById(R.id.buttonSignUpClose);


        String userName = mApp.getmUserName();
        this.setTitle(userName);

        mUserDBAdapter = new  UserDBAdapter(this);
        mUserDBAdapter = mUserDBAdapter.open();


        btnCreateNext.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                onCreateAccount();

            }
        });

    }

    private void onCreateAccount() {

        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String email = mEditTextEmailTxt.getText().toString();

        // check if any of the fields are vaccant
        if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
         //   return;
        }
        // check if both password matches
        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Password Does Not Matches", Toast.LENGTH_LONG).show();
            return;
        } else {
            // Save the Data in Database
            mApp.setmUserName(userName);
            mUserDBAdapter.insertEntry(userName, password, email );
            userPreference.writeInstanceStateUserName(this,userName);

             Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

            finish();
        }


    }
    @Override
    protected void onDone() {
        onCreateAccount();
    }

 }
