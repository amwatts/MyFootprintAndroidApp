package com.wattsup.alissa.myfootprint;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
