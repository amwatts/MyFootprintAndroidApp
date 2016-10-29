package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalcTrain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_train);
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
        saveTrainInput(param);
    }

    public void next (View view) {
        String param = "next";
        saveTrainInput(param);
    }

    private void saveTrainInput(String className) {
        Intent showNextActivvity;

        if (className.equals("add"))
        { showNextActivvity = new Intent(CalcTrain.this, CalculatorInput.class); }
        else
        { showNextActivvity = new Intent(CalcTrain.this, BuyInput.class); }

        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalcInputDataEditor = CalcInputData.edit();

        EditText trainMinutes = (EditText) findViewById(R.id.trainInputValue);
        String trainMinutesValue = trainMinutes.getText().toString();

        if (trainMinutesValue.length() == 0) {
            Toast.makeText(this, "Empty Input Value", Toast.LENGTH_SHORT).show(); }
        else {

            Double double1 = Double.parseDouble(trainMinutesValue);
            if (double1 == 0)
            { Toast.makeText(this, "Input must be greater than 0", Toast.LENGTH_SHORT).show();}
            else {

                CalcInputDataEditor.putString("trainMinutes", trainMinutesValue);
                CalcInputDataEditor.apply();
                startActivity(showNextActivvity);
            }

        }
    }

}
