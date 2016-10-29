package com.wattsup.alissa.myfootprint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalcResults extends AppCompatActivity {

    Double CO_PER_CAR_MIN = 1.2;    //pounds
    final Double CO_PER_TRAIN_MIN = 0.4;
    final Double CO_PER_BUY_WATER_BOTTLE = 2.3;
    final Double CO_PER_BUY_CAN = 0.28;
    final Double CO_PER_BUY_GLASS_BOTTLE = 0.38;
    final Double CO_PER_BUY_BEEF = 8.0;
    final Double CO_PER_BUY_PORK = 6.0;
    final Double CO_PER_BUY_OTHER_MEAT = 2.0;
    final Double CO_PER_RECY_WATERBOTTLE = 0.15;
    final Double CO_PER_RECY_CAN = 0.28;
    final Double CO_PER_RECY_GLASS_BOTTLE = 0.38;
    final Double CO_PER_RECY_BOXES = 0.04;
    final Double CO_PER_RECY_PAPERS = 0.11;
    final Double CO_PER_RECY_ELECTRONICS = 28.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_results);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

        //Receive SharedPreferences data appropriately
        String minutesDrivenValue = CalcInputData.getString("minutesDriven", "0");
        String milesPerGallonValue = CalcInputData.getString("milesPerGallon", "0");
        String peopleInCarValue = CalcInputData.getString("peopleInCar", "0");
        String trainMinutesvalue = CalcInputData.getString("trainMinutes", "0");
        String nonMotorMilesValue = CalcInputData.getString("MilesWalked", "0");
        Integer buyWaterBottles = CalcInputData.getInt("buyWBottle", 0);
        Integer buyCans = CalcInputData.getInt("buyCans", 0);
        Integer buyGlass = CalcInputData.getInt("buyGlass", 0);
        String buyBeefValue = CalcInputData.getString("buyBeef", "0");
        String buyPorkValue = CalcInputData.getString("buyPork", "0");
        String buyOtherMeatValue = CalcInputData.getString("buyOtherMeat", "0");
        Integer recyWaterBottles = CalcInputData.getInt("recyWaterBottles", 0);
        Integer recyCans = CalcInputData.getInt("recyCans", 0);
        Integer recyGlassBottles = CalcInputData.getInt("recyGlassBottles", 0);
        Integer recyBoxes = CalcInputData.getInt("recyBoxes", 0);
        Integer recyPapers = CalcInputData.getInt("recyPapers", 0);
        Integer recyElectronics = CalcInputData.getInt("recyElectronics", 0);


        //Convert Strings to Doubles to be able to calculate using approproate decimal points
        Double minutesDriven = Double.parseDouble(minutesDrivenValue);
        Double milesPerGallon = Double.parseDouble(milesPerGallonValue);
        Double peopleInCar = Double.parseDouble(peopleInCarValue);
        Double trainMinutes = Double.parseDouble(trainMinutesvalue);
        Double nonMotorMiles = Double.parseDouble(nonMotorMilesValue);
        Double buyBeef = Double.parseDouble(buyBeefValue);
        Double buyPork = Double.parseDouble(buyPorkValue);
        Double buyOtherMeat = Double.parseDouble(buyOtherMeatValue);

        if (milesPerGallon <= 20) {CO_PER_CAR_MIN = 1.8; }
        Double totalEmissionToday = 0.0;

        //Calculate to display on results activity:

        if (peopleInCar == 0)  {
            totalEmissionToday = (trainMinutes*CO_PER_TRAIN_MIN + buyWaterBottles*CO_PER_BUY_WATER_BOTTLE + buyCans*CO_PER_BUY_CAN
                    + buyGlass*CO_PER_BUY_GLASS_BOTTLE + buyBeef*CO_PER_BUY_BEEF + buyPork*CO_PER_BUY_PORK + buyOtherMeat*CO_PER_BUY_OTHER_MEAT); }
        else {
            totalEmissionToday = (minutesDriven*CO_PER_CAR_MIN)/peopleInCar + trainMinutes*CO_PER_TRAIN_MIN + buyWaterBottles*CO_PER_BUY_WATER_BOTTLE
                    + buyCans*CO_PER_BUY_CAN + buyBeef*CO_PER_BUY_BEEF + buyPork*CO_PER_BUY_PORK + buyOtherMeat*CO_PER_BUY_OTHER_MEAT;
        }
        Double TotalSaved = nonMotorMiles + recyWaterBottles*CO_PER_RECY_WATERBOTTLE + recyCans*CO_PER_RECY_CAN + recyGlassBottles*CO_PER_RECY_GLASS_BOTTLE
                +recyBoxes*CO_PER_RECY_BOXES + recyPapers*CO_PER_RECY_PAPERS + recyElectronics*CO_PER_RECY_ELECTRONICS;
        Double TotalsDifference = totalEmissionToday - TotalSaved;


        addTodaysToSharedPreferences(totalEmissionToday, TotalSaved, TotalsDifference);

        DisplayTextResults(TotalsDifference, totalEmissionToday, TotalSaved );

        DisplayRatingBar(totalEmissionToday);

    }


    private void addTodaysToSharedPreferences(double footprint, double saved, double difference) {
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor MyTotalsEditable = CalcInputData.edit();

        String footprintString = String.valueOf(footprint);
        String savedString = String.valueOf(saved);
        String diffString = String.valueOf(difference);

        MyTotalsEditable.putString("totalEmissionToday", footprintString);
        MyTotalsEditable.putString("totalSavedToday", savedString);
        MyTotalsEditable.putString("totalDifferenceToday", diffString);
        MyTotalsEditable.apply();
    }


    private void DisplayTextResults(double difference, double emission, double saved) {
        TextView answer = (TextView) findViewById(R.id.resultsValue);
        //String diffStr = Double.toString(difference);

        double diffRound = Math.round(100*difference)/((double)100);
        double emissionRound = Math.round(100*emission)/((double)100);
        double savedRound = Math.round(100*saved)/((double)100);

        answer.setText(diffRound + " lb CO₂");

        TextView display1 = (TextView) findViewById(R.id.DisplayEmissionResult);
        display1.setText("Produced: " + emissionRound + " lb CO₂");

        TextView display2 = (TextView) findViewById(R.id.DisplaySavedResult);
        display2.setText("Saved: " + savedRound + " lb CO₂");
    }

    private void DisplayRatingBar(double emissionToday) {
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setIsIndicator(true);

        if (emissionToday <= 10 ) { ratingBar.setRating(5); }
        else if (emissionToday < 30) { ratingBar.setRating(4); }
        else if (emissionToday < 60) { ratingBar.setRating(3); }
        else if (emissionToday < 150) { ratingBar.setRating(2); }
        else { ratingBar.setRating(1); }
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


    public void addToCumulTotals(View view) {
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String dateToday = dateInstance.format(Calendar.getInstance().getTime());
        String dateLastCalc = CalcInputData.getString("lastCalcDate", "0");

        if (dateToday.equals(dateLastCalc)) {
            //alert user that a number has already been saved for today
            dateAlreadySavedAlert();
        }
        else {

            //Save today's date
            saveDate();

            SharedPreferences CalculateForTotals = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

            //Get Todays Totals
            String totalFootprintTodayValue = CalculateForTotals.getString("totalDifferenceToday", "0");
            String totalEmissionTodayValue = CalculateForTotals.getString("totalEmissionToday", "0");
            String totalSavedTodayValue = CalculateForTotals.getString("totalSavedToday", "0");

            //Receive todays SharedPreferences data
            String minutesDrivenValue = CalcInputData.getString("minutesDriven", "0");
            String trainMinutesvalue = CalcInputData.getString("trainMinutes", "0");
            String nonMotorMilesValue = CalcInputData.getString("MilesWalked", "0");
            Integer buyWaterBottles = CalcInputData.getInt("buyWBottle", 0);
            Boolean buyWaterBottleStatus = CalcInputData.getBoolean("waterBottleInputBoolean", false);
            Integer buyCans = CalcInputData.getInt("buyCans", 0);
            Integer buyGlass = CalcInputData.getInt("buyGlass", 0);
            String buyBeefValue = CalcInputData.getString("buyBeef", "0");
            String buyPorkValue = CalcInputData.getString("buyPork", "0");
            String buyOtherMeatValue = CalcInputData.getString("buyOtherMeat", "0");
            Integer recyWaterBottles = CalcInputData.getInt("recyWaterBottles", 0);
            Integer recyCans = CalcInputData.getInt("recyCans", 0);
            Integer recyGlassBottles = CalcInputData.getInt("recyGlassBottles", 0);
            Integer recyBoxes = CalcInputData.getInt("recyBoxes", 0);
            Integer recyPapers = CalcInputData.getInt("recyPapers", 0);
            Integer recyElectronics = CalcInputData.getInt("recyElectronics", 0);

            //Convert Strings to Doubles to be able to calculate using approproate decimal points
            Double minutesDriven = Double.parseDouble(minutesDrivenValue);
            Double trainMinutes = Double.parseDouble(trainMinutesvalue);
            Double nonMotorMiles = Double.parseDouble(nonMotorMilesValue);
            Double buyBeef = Double.parseDouble(buyBeefValue);
            Double buyPork = Double.parseDouble(buyPorkValue);
            Double buyOtherMeat = Double.parseDouble(buyOtherMeatValue);

            //Get cumulative totals
            String totalCumulEmissionValue = CalculateForTotals.getString("totalCumulEmission", "0");
            String totalCumulSavedValue = CalculateForTotals.getString("totalCumulSaved", "0");
            String totalCumulDiffValue = CalculateForTotals.getString("totalCumulDiff", "0");
            Integer totalRecyCansHours = CalculateForTotals.getInt("totalRecyCans", 0);
            Integer cumulDaysNotBuyWB = CalculateForTotals.getInt("totalDaysNotBuyWB", 0);
            Integer cumulDaysInputted = CalculateForTotals.getInt("totalDaysCalced", 0) + 1;
            String minutesDrivenCumulValue = CalculateForTotals.getString("totalMinutesDriven", "0");
            String trainMinutesCumulValue = CalculateForTotals.getString("totalTrainMinutes", "0");
            String nonMotorMilesCumulValue = CalculateForTotals.getString("totalMilesWalked", "0");
            Integer buyWBCumul = CalculateForTotals.getInt("totalBuyWB", 0);
            Integer buyCansCumul = CalculateForTotals.getInt("totalBuyCans", 0);
            Integer buyGlassCumul = CalculateForTotals.getInt("totalBuyGlass", 0);
            String buyBeefCumulValue = CalculateForTotals.getString("totalBuyBeef", "0");
            String buyPorkCumulValue = CalculateForTotals.getString("totalBuyPork", "0");
            String buyOtherMeatCumulValue = CalculateForTotals.getString("totalBuyOtherMeat", "0");
            Integer recyWaterBottlesCumul = CalculateForTotals.getInt("totalRecyWaterBottles", 0);
            Integer recyGlassBottlesCumul = CalculateForTotals.getInt("totalRecyGlassBottles", 0);
            Integer recyBoxesCumul = CalculateForTotals.getInt("totalRecyBoxes", 0);
            Integer recyPapersCumul = CalculateForTotals.getInt("totalRecyPapers", 0);
            Integer recyElectronicsCumul = CalculateForTotals.getInt("totalRecyElectronics", 0);

            //Convert strings to doubles
            Double totalFootprintToday = Double.parseDouble(totalFootprintTodayValue);
            Double totalEmissionToday = Double.parseDouble(totalEmissionTodayValue);
            Double totalSavedToday = Double.parseDouble(totalSavedTodayValue);
            Double totalCumulEmission = Double.parseDouble(totalCumulEmissionValue);
            Double totalCumulSaved = Double.parseDouble(totalCumulSavedValue);
            Double totalCumulDiff = Double.parseDouble(totalCumulDiffValue);
            Double minutesDrivenCumul = Double.parseDouble(minutesDrivenCumulValue);
            Double trainMinutesCumul = Double.parseDouble(trainMinutesCumulValue);
            Double nonMotorMilesCumul = Double.parseDouble(nonMotorMilesCumulValue);
            Double buyBeefCumul = Double.parseDouble(buyBeefCumulValue);
            Double buyPorkCumul = Double.parseDouble(buyPorkCumulValue);
            Double buyOtherMeatCumul = Double.parseDouble(buyOtherMeatCumulValue);

            //Do the calculations for new total
            Double newTotalEmission = totalEmissionToday + totalCumulEmission;
            Double newTotalSaved = totalSavedToday + totalCumulSaved;
            Double newTotalDiff = totalFootprintToday + totalCumulDiff;
            Integer newTotalRecyCansHours = recyCans * 3 + totalRecyCansHours;
            Double newTotalMinutesDriven = minutesDriven + minutesDrivenCumul;
            Double newTotalTrainMinutes = trainMinutes + trainMinutesCumul;
            Double newTotalNonMotorMiles = nonMotorMiles + nonMotorMilesCumul;
            Integer newTotalBuyWB = buyWaterBottles + buyWBCumul;
            Integer newTotalBuyCans = buyCans + buyCansCumul;
            Integer newTotalBuyGlass = buyGlass + buyGlassCumul;
            Double newTotalBeef = buyBeef + buyBeefCumul;
            Double newTotalBuyPork = buyPork + buyPorkCumul;
            Double newTotalBuyOtherMeat = buyOtherMeat + buyOtherMeatCumul;
            Integer newTotalRecyWB = recyWaterBottles + recyWaterBottlesCumul;
            Integer newTotalRecyGlass = recyGlassBottles + recyGlassBottlesCumul;
            Integer newTotalRecyBoxes = recyBoxes + recyBoxesCumul;
            Integer newTotalRecyPapers = recyPapers + recyPapersCumul;
            Integer newTotalsRecyElectronics = recyElectronics + recyElectronicsCumul;

            if (buyWaterBottleStatus) cumulDaysNotBuyWB = 0;
            else cumulDaysNotBuyWB++;

            SaveNewTotalsInSharedPreferences(newTotalEmission, newTotalSaved, newTotalDiff, newTotalRecyCansHours, cumulDaysNotBuyWB,
                                cumulDaysInputted, newTotalMinutesDriven, newTotalTrainMinutes, newTotalNonMotorMiles, newTotalBuyCans,
                                newTotalBeef, newTotalBuyPork, newTotalBuyOtherMeat, newTotalRecyWB, newTotalRecyGlass, newTotalRecyBoxes,
                                newTotalRecyPapers, newTotalsRecyElectronics, newTotalBuyWB, newTotalBuyGlass);

            Toast.makeText(this, "Number saved", Toast.LENGTH_SHORT).show();

        }
    }

    private void dateAlreadySavedAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Number already saved");
        builder.setMessage("You cannot save two numbers in the same day");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    private void saveDate() {
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String date = dateInstance.format(Calendar.getInstance().getTime());

        SharedPreferences CalculateForTotals = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalculateForTotalsEditor = CalculateForTotals.edit();

        CalculateForTotalsEditor.putString("lastCalcDate", date);
        CalculateForTotalsEditor.apply();
    }

    private void SaveNewTotalsInSharedPreferences(Double totalEmission, Double totalSaved, Double totalDiff, Integer totalRecyCansHours,
                                                  Integer daysNotBuyWB, Integer daysInputted, Double totalMinutesDriven, Double totalTrainMinutes,
                                                  Double totalNonMotorMiles, Integer totalBuyCans, Double totalBeef, Double totalPork,
                                                  Double totalOtherMeat, Integer totalRecyWB, Integer totalRecyGlass, Integer totalRecyBoxes,
                                                  Integer totalRecyPapers, Integer totalRecyElectronics, Integer totalBuyWB, Integer totalBuyGlass ) {
        SharedPreferences CalculateForTotals = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalculateForTotalsEditor = CalculateForTotals.edit();

        //Convert doubles to Strings
        String totalEmissionString = String.valueOf(totalEmission);
        String totalSavedString = String.valueOf(totalSaved);
        String totalDiffString = String.valueOf(totalDiff);
        String totalMinutesDrivenString = String.valueOf(totalMinutesDriven);
        String totalTrainMinutesString = String.valueOf(totalTrainMinutes);
        String totalNonMotorMilesString = String.valueOf(totalNonMotorMiles);
        String totalBeefString = String.valueOf(totalBeef);
        String totalPorkString = String.valueOf(totalPork);
        String totalOtherMeatString = String.valueOf(totalOtherMeat);

        CalculateForTotalsEditor.putString("totalCumulEmission", totalEmissionString);
        CalculateForTotalsEditor.putString("totalCumulSaved", totalSavedString);
        CalculateForTotalsEditor.putString("totalCumulDiff", totalDiffString);
        CalculateForTotalsEditor.putInt("totalRecyCansHours", totalRecyCansHours);
        CalculateForTotalsEditor.putInt("totalDaysNotBuyWB", daysNotBuyWB);
        CalculateForTotalsEditor.putInt("totalDaysCalced", daysInputted);
        CalculateForTotalsEditor.putString("totalMinutesDriven", totalMinutesDrivenString);
        CalculateForTotalsEditor.putString("totalTrainMinutes", totalTrainMinutesString);
        CalculateForTotalsEditor.putString("totalMilesWalked", totalNonMotorMilesString);
        CalculateForTotalsEditor.putInt("totalBuyCans", totalBuyCans);
        CalculateForTotalsEditor.putString("totalBuyBeef", totalBeefString);
        CalculateForTotalsEditor.putString("totalBuyPork", totalPorkString);
        CalculateForTotalsEditor.putString("totalBuyOtherMeat", totalOtherMeatString);
        CalculateForTotalsEditor.putInt("totalRecyWaterBottles", totalRecyWB);
        CalculateForTotalsEditor.putInt("totalRecyGlassBottles", totalRecyGlass);
        CalculateForTotalsEditor.putInt("totalRecyBoxes", totalRecyBoxes);
        CalculateForTotalsEditor.putInt("totalRecyPapers", totalRecyPapers);
        CalculateForTotalsEditor.putInt("totalRecyElectronics", totalRecyElectronics);
        CalculateForTotalsEditor.putInt("totalBuyWB", totalBuyWB);
        CalculateForTotalsEditor.putInt("totalBuyGlass", totalBuyGlass);

        CalculateForTotalsEditor.apply();
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
