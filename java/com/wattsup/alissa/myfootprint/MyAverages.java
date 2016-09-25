package com.wattsup.alissa.myfootprint;

import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyAverages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_averages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get saved totals from calc results page
        SharedPreferences myAveragesCalculated = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

        String totalCumulDiffValue = myAveragesCalculated.getString("totalCumulDiff", "0");
        String totalCumulEmissionValue = myAveragesCalculated.getString("totalCumulEmission", "0");
        String totalCumulSavedValue = myAveragesCalculated.getString("totalCumulSaved", "0");

        Integer totalCumulDays = myAveragesCalculated.getInt("totalDaysCalced", 0);
        String totalMinutesDrivenValue = myAveragesCalculated.getString("totalMinutesDriven", "0");
        String totalTrainMinutesValue = myAveragesCalculated.getString("totalTrainMinutes", "0");
        String totalNonMotorMilesValue = myAveragesCalculated.getString("totalMilesWalked", "0");
        Integer totalBuyWB = myAveragesCalculated.getInt("totalBuyWB", 0);
        Integer totalBuyCans = myAveragesCalculated.getInt("totalBuyCans", 0);
        Integer totalBuyGlass = myAveragesCalculated.getInt("totalBuyGlass", 0);
        String totalBuyBeefValue = myAveragesCalculated.getString("totalBuyBeef", "0");
        String totalBuyPorkValue = myAveragesCalculated.getString("totalBuyPork", "0");
        String totalOtherMeatValue = myAveragesCalculated.getString("totalBuyOtherMeat", "0");
        Integer totalRecyWB = myAveragesCalculated.getInt("totalRecyWaterBottles", 0);
        Integer totalRecyGlass = myAveragesCalculated.getInt("totalRecyGlassBottles", 0);
        Integer totalRecyBoxes = myAveragesCalculated.getInt("totalRecyBoxes", 0);
        Integer totalRecyPapers = myAveragesCalculated.getInt("totalRecyPapers", 0);
        Integer totalRecyElectronics = myAveragesCalculated.getInt("totalRecyElectronics", 0);

        //Convert Strings to Doubles
        Double totalCumulDiff = Double.parseDouble(totalCumulDiffValue);
        Double totalCumulEmission = Double.parseDouble(totalCumulEmissionValue);
        Double totalCumulSaved = Double.parseDouble(totalCumulSavedValue);
        Double totalMinutesDriven = Double.parseDouble(totalMinutesDrivenValue);
        Double totalTrainMinutes = Double.parseDouble(totalTrainMinutesValue);
        Double totalNonMotorMiles = Double.parseDouble(totalNonMotorMilesValue);
        Double totalBuyBeef = Double.parseDouble(totalBuyBeefValue);
        Double totalBuyPork = Double.parseDouble(totalBuyPorkValue);
        Double totalOtherMeat = Double.parseDouble(totalOtherMeatValue);

        TextView averagesBanner = (TextView) findViewById(R.id.averageBanner);
        averagesBanner.setText("My averages for the past " + totalCumulDays + " days");

        ListView listView = (ListView) findViewById(R.id.listView);
        Context context;

        //Create a list of items
        //Make sure total cumulative days is not 0 because you cannot divide by 0
        if (totalCumulDays != 0) {
            //Do the calculations
            Double averageDifference = totalCumulDiff / totalCumulDays;
            Double averageEmission = totalCumulEmission / totalCumulDays;
            Double averageSaved = totalCumulSaved / totalCumulDays;
            Double averageMinutesDriven = totalMinutesDriven/ totalCumulDays;
            Double averageTrainMinutes = totalTrainMinutes/ totalCumulDays;
            Double averageNonMotorMiles = totalNonMotorMiles/ totalCumulDays;
            Integer averageBuyWB = totalBuyWB/ totalCumulDays;
            Integer averageBuyCans = totalBuyCans/ totalCumulDays;
            Integer averageBuyGlass = totalBuyGlass/ totalCumulDays;
            Double averageBuyBeef = totalBuyBeef/ totalCumulDays;
            Double averageBuyPork = totalBuyPork/ totalCumulDays;
            Double averageBuyOtherMeat = totalOtherMeat/ totalCumulDays;
            Integer averageRecyWB = totalRecyWB/ totalCumulDays;
            Integer averageRecyGlass = totalRecyGlass/ totalCumulDays;
            Integer averageRecyBoxes = totalRecyBoxes/ totalCumulDays;
            Integer averageRecyPapers = totalRecyPapers/ totalCumulDays;
            Integer averageRecyElectronics = totalRecyElectronics/ totalCumulDays;

/*
            String [] values = { averageDifference.toString(), averageEmission.toString(), averageSaved.toString(), averageMinutesDriven.toString(), averageTrainMinutes.toString(),
                    averageNonMotorMiles.toString(), averageBuyWB.toString(), averageBuyCans.toString(), averageBuyGlass.toString(), averageBuyBeef.toString(), averageBuyPork.toString(), averageBuyOtherMeat.toString(),
                    averageRecyWB.toString(), averageRecyGlass.toString(), averageRecyBoxes.toString(), averageRecyPapers.toString(), averageRecyElectronics.toString()};
*/
            String [] values = { roundDouble(averageDifference), roundDouble(averageEmission), roundDouble(averageSaved), roundDouble(averageMinutesDriven), roundDouble(averageTrainMinutes),
                    roundDouble(averageNonMotorMiles), averageBuyWB.toString(), averageBuyCans.toString(), averageBuyGlass.toString(), roundDouble(averageBuyBeef), roundDouble(averageBuyPork), roundDouble(averageBuyOtherMeat),
                    averageRecyWB.toString(), averageRecyGlass.toString(), averageRecyBoxes.toString(), averageRecyPapers.toString(), averageRecyElectronics.toString()};

            String [] NameList={"Carbon footprint", "Carbon emission produced", "Carbon emission saved", "In a car", "On a train", "Walking/Biking",
                    "Plastic bottles", "Aluminum cans", "Glass bottles", "Beef", "Pork", "Chicken/Fish", "Water bottles", "Glass bottles", "Boxes", "Magazines/Newspapers", "Electronics"};

            String [] unitLabels = {"lb CO₂", "lb CO₂", "lb CO₂", "Minutes", "Minutes", "Miles", "Bought", "Bought", "Bought", "lb Bought", "lb Bought", "lb Bought",
                    "Recycled", "Recycled", "Recycled", "Recycled", "Recycled"};

            context = this;
            listView.setAdapter(new CustomAdapter(this, NameList, values, unitLabels));
        }
        else {
            String[] labels={"Not enough data, save a calculation" };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_line_list_view, labels);
            listView.setAdapter(adapter);
        }

    }

    private String roundDouble(double input) {
        double result = Math.round(100*input)/((double)100);
        String resultsString = Double.toString(result);
        return resultsString;
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
