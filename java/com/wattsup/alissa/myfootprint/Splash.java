package com.wattsup.alissa.myfootprint;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.View;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getSupportActionBar().hide();
        ActionBar actionBar = getActionBar();
        //actionBar.hide();

        final Handler splashScreen = new Handler();
        splashScreen.postDelayed(new Runnable() {
            public void run() {
                Intent mInHome = new Intent(Splash.this, MainActivity.class);
                startActivity(mInHome);
                finish();
            }
        }, 3000);
    }
}
