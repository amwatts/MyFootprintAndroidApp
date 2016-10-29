package com.wattsup.alissa.myfootprint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatabaseNumbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_numbers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView databaseBanner = (TextView) findViewById(R.id.databaseBanner);
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String date = dateInstance.format(Calendar.getInstance().getTime());
        databaseBanner.setText( date + "\nFor each....");

        String[] numName = {
                "1.6",  "0.4", "2.3", "0.28", "0.38", "8.0", "6.0", "2.0", "0.15", "0.28", "0.38", "0.04", "0.11", "28.0", "3"
        };

        String[] itemName = {
                "Minute in a car", "Minute in a train", "Water bottle bought", "Per aluminum can bought", "Per glass bottle bought",
                "Per pound beef bought", "Per pound pork bought", "Per pound chicken/fish bought",
                "Per water bottle recycled", "Per aluminum can recycled", "Per glass bottle recycled", "Per cardboard box recycled",
                "Per newspaper/magazine recycled", "Per average electronic recycled", "Per aluminum can recycled"
        };

        String[] unitName = {
                "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted",
                "Pounds of CO₂ saved", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted", "Pounds of CO₂ emitted",
                "Hours a TV can be powered",
        };

        ListView listView = (ListView) findViewById(R.id.listView);


        Context context = this;
        listView.setAdapter(new CustomAdapter(this, itemName, numName, unitName));
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
