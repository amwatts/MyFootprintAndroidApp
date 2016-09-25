package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class CalcCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        saveCarInput(param);
    }

    public void next (View view) {
        String param = "next";
        saveCarInput(param);
    }

    private void saveCarInput(String className) {
        Intent showNextActivity;
        if (className.equals("add"))
        { showNextActivity = new Intent(CalcCar.this, CalculatorInput.class); }
        else
        { showNextActivity = new Intent(CalcCar.this, BuyInput.class); }

        //Create/initiate an instance of SharedPreferences called CarData
        SharedPreferences.Editor CalcInputDataEditor = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE).edit();

        //Access EditTexts
        EditText minutesDriven = (EditText) findViewById(R.id.CarInputValue1);
        EditText milesPerGallon = (EditText) findViewById(R.id.CarInputValue2);
        EditText peopleInCar = (EditText) findViewById(R.id.CarInputValue3);

        //Get value of EditTexts
        String minutesDrivenValue = minutesDriven.getText().toString();
        String milesPerGallonValue = milesPerGallon.getText().toString();
        String peopleInCarValue = peopleInCar.getText().toString();

        //Get Int value from string


        if (minutesDriven.length() == 0 || milesPerGallon.length() == 0 || peopleInCar.length() == 0) {
            Toast.makeText(this, "Empty Input Value", Toast.LENGTH_SHORT).show(); }
        else {

            Double double1 = Double.parseDouble(minutesDrivenValue);
            Double double2 = Double.parseDouble(milesPerGallonValue);
            Double double3 = Double.parseDouble(peopleInCarValue);

            if (double1 == 0 || double2 == 0 || double3 < 1)
            { Toast.makeText(this, "One or more inputs must be greater", Toast.LENGTH_SHORT).show(); }

            else {
                //Save Integer value in Shared Preferences
                CalcInputDataEditor.putString("minutesDriven", minutesDrivenValue);
                CalcInputDataEditor.putString("milesPerGallon", milesPerGallonValue);
                CalcInputDataEditor.putString("peopleInCar", peopleInCarValue);

                //Use SharedPreferences to save car data
                CalcInputDataEditor.apply();
                startActivity(showNextActivity);
            }
        }
    }

}
