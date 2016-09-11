package com.tennis.gameprofiler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class NewTournamentActivity extends GameProfilerBaseClass {

    Button mBtnNewMatch;
    Button mBtnSave;
    TournamentDBAdapter tournamentDBAdapter;
    EditText mEditTextName;
    EditText mEditTextPlace;
    EditText mEditTextCourtType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tournament);

        tournamentDBAdapter = new TournamentDBAdapter(this);


        String userName = mApp.getmUserName();
        this.setTitle(userName);

//        onCreateAdView();


        mEditTextName = (EditText)findViewById(R.id.editTextTournamentName);
        mEditTextPlace = (EditText)findViewById(R.id.editTextTournamentPlace);
        mEditTextCourtType = (EditText)findViewById(R.id.editTextCourtType);
        mEditTextName.setOnKeyListener(new View.OnKeyListener() {
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
        mEditTextCourtType.setOnKeyListener(new View.OnKeyListener() {
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
        mEditTextPlace.setOnKeyListener(new View.OnKeyListener() {
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




    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        onBtnSave();

    }

    public void onBtnSave() {

        String tournamentName = mEditTextName.getText().toString();
        String tournamentPlace =  mEditTextPlace.getText().toString();
        String courtType = mEditTextCourtType.getText().toString();

        if(tournamentName.isEmpty()) {
            Toast.makeText(this,"Please enter Tournament name", Toast.LENGTH_SHORT);
            return;
        }

        tournamentDBAdapter = tournamentDBAdapter.open();
        tournamentDBAdapter.insertEntry(mEditTextName.getText().toString(),
                                        mEditTextPlace.getText().toString(),
                                        mEditTextCourtType.getText().toString());
        finish();

    }
}
