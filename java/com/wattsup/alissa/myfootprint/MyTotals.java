package com.wattsup.alissa.myfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MyTotals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_totals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
