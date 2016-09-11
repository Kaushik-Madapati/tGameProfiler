package com.tennis.gameprofiler;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button btnCloseBtn = (Button)findViewById(R.id.btnHelpClose);
        btnCloseBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }

}
