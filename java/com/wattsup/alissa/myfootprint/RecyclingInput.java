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

public class RecyclingInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_input);
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

    public void next (View view) {
        Intent ShowRecyCalcResultsActivity = new Intent(this, Calculating.class);
        SharedPreferences RecyInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor RecyInputDataEditor = RecyInputData.edit();

        //Access EditTexts by id
        EditText WaterBottles = (EditText) findViewById(R.id.RecyWaterBottleInput);
        EditText Cans = (EditText) findViewById(R.id.RecyCansInput);
        EditText GlassBottles = (EditText) findViewById(R.id.RecyGlassBottlesInput);
        EditText Boxes = (EditText) findViewById(R.id.RecyBoxesInput);
        EditText Papers = (EditText) findViewById(R.id.RecyPapersInput);
        EditText Electronics = (EditText) findViewById(R.id.RecyElectInput);

        //Retrieve String Value from EditText
        String RecyWaterBottlesValue = WaterBottles.getText().toString();
        String RecyCansValue = Cans.getText().toString();
        String RecyGlassBottlesValue = GlassBottles.getText().toString();
        String RecyBoxesValue = Boxes.getText().toString();
        String RecyPapersValue = Papers.getText().toString();
        String RecyElectronicsValue = Electronics.getText().toString();


        if (RecyWaterBottlesValue.length() == 0 || RecyCansValue.length() == 0 || RecyGlassBottlesValue.length() == 0 || RecyBoxesValue.length() == 0 || RecyPapersValue.length() == 0|| RecyElectronicsValue.length() == 0 )
        {
            Toast.makeText(this, "Empty Input Value", Toast.LENGTH_SHORT).show(); }
        else {
            //Parse String value into Integer value
            Integer RecyWaterBottleInt = Integer.parseInt(RecyWaterBottlesValue);
            Integer RecyCansInt = Integer.parseInt(RecyCansValue);
            Integer RecyGlassBottlesInt = Integer.parseInt(RecyGlassBottlesValue);
            Integer RecyBoxesInt = Integer.parseInt(RecyBoxesValue);
            Integer RecyPapersInt = Integer.parseInt(RecyPapersValue);
            Integer RecyElectronicsInt = Integer.parseInt(RecyElectronicsValue);

            //Save Integer value in Shared Preferences
            RecyInputDataEditor.putInt("recyWaterBottles", RecyWaterBottleInt);
            RecyInputDataEditor.putInt("recyCans", RecyCansInt);
            RecyInputDataEditor.putInt("recyGlassBottles", RecyGlassBottlesInt);
            RecyInputDataEditor.putInt("recyBoxes", RecyBoxesInt);
            RecyInputDataEditor.putInt("recyPapers", RecyPapersInt);
            RecyInputDataEditor.putInt("recyElectronics", RecyElectronicsInt);

            RecyInputDataEditor.apply();
            startActivity(ShowRecyCalcResultsActivity);

        }
    }

}
