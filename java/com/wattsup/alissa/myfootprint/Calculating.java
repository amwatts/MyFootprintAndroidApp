package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class Calculating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculating);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final Handler calculatingScreen = new Handler();
        calculatingScreen.postDelayed(new Runnable() {
            public void run() {
                Intent mInHome = new Intent(Calculating.this, CalcResults.class);
                startActivity(mInHome);
                finish();
            }
        }, 2000);

    }

}
