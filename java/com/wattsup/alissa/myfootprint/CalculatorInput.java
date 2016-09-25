package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CalculatorInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_input);
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


    public void ViewCalcCarActivity (View view) {
        Intent showCalcCarActivity = new Intent (this, CalcCar.class);
        startActivity(showCalcCarActivity);
    }

    public void ViewCalcTrainActivity (View view) {
        Intent ShowCalcTrainActivity = new Intent(this, CalcTrain.class);
        startActivity(ShowCalcTrainActivity);
    }

    public void ViewCalcNonMotorActivity (View view) {
        Intent ShowCalcNonMotorActivity = new Intent(this, CalcNonMotor.class);
        startActivity(ShowCalcNonMotorActivity);
    }

    public void ViewCalcBoughtActivity (View view) {
        Intent ShowCalcBoughtActivity = new Intent(this, BuyInput.class);
        startActivity(ShowCalcBoughtActivity);
    }

    public void viewMyTotals(View view) {
        Intent myTotals = new Intent(this, MyTotals.class);
        startActivity(myTotals);
    }

    public void goHome(View view) {
        Intent homePage = new Intent(this, MainActivity.class);
        startActivity(homePage);
    }

    public void viewMyAverages(View view) {
        Intent myAverages = new Intent(this, MyAverages.class);
        startActivity(myAverages);
    }

    public void viewDatabaseNumbers(View view) {
        Intent databaseNumber = new Intent(this, DatabaseNumbers.class);
        startActivity(databaseNumber);
    }

    public void goToRecyclables(View view) {
        Intent intent = new Intent(this, Recyclables.class);
        startActivity(intent);
    }
}
