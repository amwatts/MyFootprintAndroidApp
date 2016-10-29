package com.wattsup.alissa.myfootprint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.action_tips_tricks:
                Intent intent3 = new Intent(this, Tips.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToCalc(View view) {
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String dateToday = dateInstance.format(Calendar.getInstance().getTime());
        String dateLastCalc = CalcInputData.getString("lastCalcDate", "0");

        //notifyUser();

        if (dateToday.equals(dateLastCalc)) {
            dialogBoxResponse();
        }
        else {
            //make all transportation equal to 0
            initiateTransportationToZero();

            Intent displayCalc = new Intent(this, CalculatorInput.class);
            startActivity(displayCalc);
        }
    }

    private void dialogBoxResponse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Number already saved today");
        builder.setMessage("You will not be able to save another number today, would you like to calculate anyways?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //make all transportation equal to 0
                initiateTransportationToZero();

                Intent displayCalc = new Intent(MainActivity.this, CalculatorInput.class);
                startActivity(displayCalc);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initiateTransportationToZero() {
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalcInputDataEditor = CalcInputData.edit();

        CalcInputDataEditor.putString("trainMinutes", "0");
        CalcInputDataEditor.putString("MilesWalked", "0");
        CalcInputDataEditor.putString("minutesDriven", "0");
        CalcInputDataEditor.putString("milesPerGallon", "0");
        CalcInputDataEditor.putString("peopleInCar", "0");

        CalcInputDataEditor.apply();
    }

    public void viewMyTotals(View view) {
        Intent displayTotals = new Intent(this, MyTotals.class);
        startActivity(displayTotals);
    }

    public void viewMyAverages(View view) {
        Intent displayMyAverages = new Intent(this, MyAverages.class);
        startActivity(displayMyAverages);
    }

    public void goHome(View view) {
        Intent displayHome = new Intent(this, MainActivity.class);
        startActivity(displayHome);
    }

    public void viewDatabaseNumbers(View view) {
        Intent displayDatabaseNumbers = new Intent(this, DatabaseNumbers.class);
        startActivity(displayDatabaseNumbers);
    }

    public void goToRecyclables(View view) {
        Intent intent = new Intent(this, Recyclables.class);
        startActivity(intent);
    }
}
