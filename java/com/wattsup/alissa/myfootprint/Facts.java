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
import android.widget.TextView;

import java.util.Calendar;

public class Facts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.factOrFictText);
        //TextView answerView = (TextView) findViewById(R.id.factAnswer);
        String[] factsArray = getResources().getStringArray(R.array.Facts);

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        textView.setText(factsArray[dayOfMonth]);

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
