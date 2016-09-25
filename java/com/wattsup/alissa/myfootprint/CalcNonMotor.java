package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalcNonMotor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_non_motor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_fact_fiction:
                Intent intent2 = new Intent(this, Facts.class);
                startActivity(intent2);
                return true;
            case R.id.action_tips_tricks:
                Intent intent3 = new Intent(this, Tips.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addAnotherTrans(View view) {
        String param = "add";
        saveNonMotorInput(param);
    }

    public void next (View view) {
        String param = "next";
        saveNonMotorInput(param);
    }

    private void saveNonMotorInput(String className) {
        Intent ShowNextActivity;
        if (className.equals("add"))
        { ShowNextActivity = new Intent(CalcNonMotor.this, CalculatorInput.class);}
        else
        { ShowNextActivity = new Intent(CalcNonMotor.this, BuyInput.class); }

        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalcInputDataEditor = CalcInputData.edit();

        EditText nonMotorMiles = (EditText) findViewById(R.id.nonMotInputValue);
        String nonMotorMilesValue = nonMotorMiles.getText().toString();

        if (nonMotorMilesValue.length() == 0) {
            Toast.makeText(this, "Empty Input Value", Toast.LENGTH_SHORT).show(); }
        else {

            Double double1 = Double.parseDouble(nonMotorMilesValue);
            if (double1 == 0)
            {Toast.makeText(this, "Input must be greater than 0", Toast.LENGTH_SHORT).show();}
            else
            {
                CalcInputDataEditor.putString("MilesWalked", nonMotorMilesValue);
                CalcInputDataEditor.apply();
                startActivity(ShowNextActivity);
            }
        }
    }

}
