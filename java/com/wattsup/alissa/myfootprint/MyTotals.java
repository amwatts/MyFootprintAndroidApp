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
import android.widget.ListView;
import android.widget.TextView;

public class MyTotals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_totals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences CalculateForTotals = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);

        //get shared preferences
        String newTotalEmissionValue = CalculateForTotals.getString("totalCumulEmission", "0");
        String newTotalSavedValue = CalculateForTotals.getString("totalCumulSaved", "0");
        String newTotalDiffValue = CalculateForTotals.getString("totalCumulDiff", "0");

        Integer newTotalRecyCansHours = CalculateForTotals.getInt("totalRecyCansHours", 0);
        Integer cumulDaysNotBuyWB = CalculateForTotals.getInt("totalDaysNotBuyWB", 0);
        Integer cumulDays = CalculateForTotals.getInt("totalDaysCalced", 0);
        String totalMinutesDrivenValue = CalculateForTotals.getString("totalMinutesDriven", "0");
        String totalTrainMinutesValue = CalculateForTotals.getString("totalTrainMinutes", "0");
        String totalNonMotorMilesValue = CalculateForTotals.getString("totalMilesWalked", "0");
        Integer totalBuyWB = CalculateForTotals.getInt("totalBuyWB", 0);
        Integer totalBuyCans = CalculateForTotals.getInt("totalBuyCans", 0);
        Integer totalBuyGlass = CalculateForTotals.getInt("totalBuyGlass", 0);
        String totalBuyBeefValue = CalculateForTotals.getString("totalBuyBeef", "0");
        String totalBuyPorkValue = CalculateForTotals.getString("totalBuyPork", "0");
        String totalOtherMeatValue = CalculateForTotals.getString("totalBuyOtherMeat", "0");
        Integer totalRecyWB = CalculateForTotals.getInt("totalRecyWaterBottles", 0);
        Integer totalRecyGlass = CalculateForTotals.getInt("totalRecyGlassBottles", 0);
        Integer totalRecyBoxes = CalculateForTotals.getInt("totalRecyBoxes", 0);
        Integer totalRecyPapers = CalculateForTotals.getInt("totalRecyPapers", 0);
        Integer totalRecyElectronics = CalculateForTotals.getInt("totalRecyElectronics", 0);

        TextView totalsBanner = (TextView) findViewById(R.id.totalsBanner);
        totalsBanner.setText("My totals for the past " + cumulDays + " days");

        ListView listView = (ListView) findViewById(R.id.listView);

/*        String [] values = { newTotalDiffValue, newTotalEmissionValue, newTotalSavedValue, newTotalRecyCansHours.toString(), cumulDaysNotBuyWB.toString(),
                totalMinutesDrivenValue, totalTrainMinutesValue, totalNonMotorMilesValue, totalBuyCans.toString(), totalBuyWB.toString(), totalBuyGlass.toString(), totalBuyBeefValue,
                totalBuyPorkValue, totalOtherMeatValue, totalRecyWB.toString(), totalRecyGlass.toString(), totalRecyBoxes.toString(),totalRecyPapers.toString(), totalRecyElectronics.toString() };
*/
        String [] values = { roundDouble(newTotalDiffValue), roundDouble(newTotalEmissionValue), roundDouble(newTotalSavedValue), newTotalRecyCansHours.toString(), cumulDaysNotBuyWB.toString(),
                roundDouble(totalMinutesDrivenValue), roundDouble(totalTrainMinutesValue), roundDouble(totalNonMotorMilesValue), totalBuyCans.toString(), totalBuyWB.toString(), totalBuyGlass.toString(), roundDouble(totalBuyBeefValue),
                roundDouble(totalBuyPorkValue), roundDouble(totalOtherMeatValue), totalRecyWB.toString(), totalRecyGlass.toString(), totalRecyBoxes.toString(),totalRecyPapers.toString(), totalRecyElectronics.toString() };

        String [] NameList={"Carbon footprint", "Carbon emission produced", "Carbon emission saved", "TV hours ran", "Not buying a water bottle", "In a car",
                "On a train", "Walking/biking", "Aluminum cans", "Plastic bottles", "Glass bottles", "Beef", "Pork", "Chicken/Fish", "Water bottles", "Glass bottles", "Boxes", "Magazines/Newspapers"};

        String [] unitLabels = {"lb CO₂", "lb CO₂", "lb CO₂", "by recycling 1 aluminum can", "Cumulative days", "Minutes", "Minutes", "Miles", "Bought", "Bought", "Bought", "lb Bought",
                "lb Bought", "lb Bought", "Recycled", "Recycled", "Recycled", "Recycled"};

        Context context = this;
        listView.setAdapter(new CustomAdapter(this, NameList, values, unitLabels));

    }

    private String roundDouble(String input) {
        Double inputDouble = Double.parseDouble(input);
        double result = Math.round(100*inputDouble)/((double)100);
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
