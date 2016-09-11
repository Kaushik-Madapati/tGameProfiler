package com.tennis.gameprofiler;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by nmadapati on 8/30/2016.
 */
public class GameProfilerBaseClass extends AppCompatActivity {
    UserPreference userPreference = null;
    protected GameProfilerApp mApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userPreference = new UserPreference();
        mApp = (GameProfilerApp)getApplicationContext();
        // Load an ad into the AdMob banner view.
        ;

    }

    public void onCreateAdView() {
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.reset:
            onBackToMenu();
            //add the function to perform here
            return(true);
        case R.id.about:
            onAbout();
            return(true);
        case R.id.exit:
            //add the function to perform here

            onExit();
            return(true);
        case R.id.done:
            onDone();
            return (true);
        case R.id.logout :
            userPreference.writeInstanceState(this, false);
            //          userPreference.writeInstanceStateUserName(this, "");
            onBackToMenu();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    private void  onBackToMenu() {

        Intent intent = new Intent(this,
                MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();

    }
    public void SetStatTitle(String title ) {
        TextView statTile = (TextView)findViewById(R.id.txtPracticeHeader);
        statTile.setText(title);

    }

    protected void onDone(){
        finish();
    }

    public void onAbout() {

        Intent intent = new Intent(this,
                AboutActivity.class);
        startActivity(intent);


    }
    public void onExit (){
     Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    this.finish();
    mApp.setmExitFlag(1);
    startActivity(intent);
    }
}
