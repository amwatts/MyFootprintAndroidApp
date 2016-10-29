package com.wattsup.alissa.myfootprint;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class BuyInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_input);
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

    public void next (View view) {

        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalcInputDataEditor = CalcInputData.edit();

        //Get the Edit Texts from id
        EditText BuyWBottles = (EditText) findViewById(R.id.CalcBuyInput1);
        EditText BuyCans = (EditText) findViewById(R.id.CalcBuyInput2);
        EditText BuyGlass = (EditText) findViewById(R.id.CalcBuyInput6);
        EditText BuyBeef = (EditText) findViewById(R.id.CalcBuyInput3);
        EditText BuyPork = (EditText) findViewById(R.id.CalcBuyInput4);
        EditText buyOtherMeat = (EditText) findViewById(R.id.CalcBuyInput5);


        //Get the String value of the EditTexts
        String BuyWBottlesValue = BuyWBottles.getText().toString();
        String BuyCansValue = BuyCans.getText().toString();
        String BuyGlassValue = BuyGlass.getText().toString();
        String BuyBeefValue = BuyBeef.getText().toString();
        String BuyPorkValue = BuyPork.getText().toString();
        String BuyOtherMeatValue = buyOtherMeat.getText().toString();

        //Make sure none of the EditTexts are empty
        if (BuyWBottlesValue.length() == 0 || BuyCansValue.length() == 0 || BuyGlassValue.length() == 0 || BuyBeefValue.length() == 0 || BuyPorkValue.length() == 0 || BuyOtherMeatValue.length() == 0) {
            Toast.makeText(this, "Empty Input Value", Toast.LENGTH_SHORT).show();
        }
        else {

            //Parse the String value into an Integer
            Integer BuyWBottlesInt = Integer.parseInt(BuyWBottlesValue);
            Integer BuyCansInt = Integer.parseInt(BuyCansValue);
            Integer BuyGlassInt = Integer.parseInt(BuyGlassValue);

            //Add Integer value to shared preferences
            CalcInputDataEditor.putInt("buyWBottle", BuyWBottlesInt);
            CalcInputDataEditor.putInt("buyCans", BuyCansInt);
            CalcInputDataEditor.putInt("buyGlass", BuyGlassInt);
            CalcInputDataEditor.putString("buyBeef", BuyBeefValue);
            CalcInputDataEditor.putString("buyPork", BuyPorkValue);
            CalcInputDataEditor.putString("buyOtherMeat", BuyOtherMeatValue);
            CalcInputDataEditor.apply();

            if (BuyWBottlesInt > 0)
            {
                determineWaterBottleBought();
            }
            else
            {
                Intent showRecycleInputActivity = new Intent(this, RecyclingInput.class);
                startActivity(showRecycleInputActivity);
            }

        }

    }

    private void determineWaterBottleBought() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Plastic bottle");
        builder.setMessage("Was one or more plastic bottle bought a disposable plastic water bottle?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //save boolean value of 1
                saveWaterBottleStatus(true);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save boolean value of 0
                saveWaterBottleStatus(false);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void saveWaterBottleStatus(boolean status) {
        SharedPreferences CalcInputData = getSharedPreferences("CALC_INPUT_DATA", MODE_PRIVATE);
        SharedPreferences.Editor CalcInputDataEditor = CalcInputData.edit();
        Boolean input = status;

        CalcInputDataEditor.putBoolean("waterBottleInputBoolean", input);
        CalcInputDataEditor.apply();

        Intent showRecycleInputActivity = new Intent(this, RecyclingInput.class);
        startActivity(showRecycleInputActivity);
    }

    public void goHome(View view) {
        Intent displayHome = new Intent(this, MainActivity.class);
        startActivity(displayHome);
    }

}
