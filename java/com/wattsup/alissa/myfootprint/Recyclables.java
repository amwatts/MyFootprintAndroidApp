package com.wattsup.alissa.myfootprint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class Recyclables extends AppCompatActivity {

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclables);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        setGroupParents();
        setChildData();

        ExpandableAdapter adapter = new ExpandableAdapter(parentItems, childItems);

        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableList.setAdapter(adapter);
        //expandableList.setOnChildClickListener(MyExpandableAdapter.class);
    }

    public void setGroupParents() {
        parentItems.add("Aluminum");
        parentItems.add("Paper");
        parentItems.add("Plastic");
        parentItems.add("Glass");
        parentItems.add("Light bulbs");
        parentItems.add("Batteries");
        parentItems.add("Electronics");
    }

    public void setChildData() {

        // Aluminum
        ArrayList<String> child = new ArrayList<String>();
        child.add("Aluminum can");
        child.add("Aluminum foil");
        child.add("Steel/tin can");
        child.add("Metal lids: non-reclyclable");
        childItems.add(child);

        // Papers
        child = new ArrayList<String>();
        child.add("Mixed papers");
        child.add("Newspaper");
        child.add("Corrugated cardboard");
        child.add("Cardboard cartons");
        child.add("Paperboard");
        child.add("Office paper");
        child.add("Phone book");
        child.add("Magazines");
        childItems.add(child);

        // Plastic
        child = new ArrayList<String>();
        child.add("Plastic bag - see location");
        child.add("Plastic bottle");
        child.add("Plastic caps");
        child.add("Plastic jars");
        child.add("Plastic jugs");
        child.add("Styrofoam");
        childItems.add(child);

        // Glass
        child = new ArrayList<String>();
        child.add("Clear flint glass");
        child.add("Brown amber glass");
        child.add("Green emerald glass");
        child.add("Mixed-color glass: non-recyclable");
        child.add("Heat resistant glass: non-recyclable");
        child.add("Mirror glass: non-recyclable");
        child.add("Ceramics: non-recyclable");
        child.add("Window glass: non-recyclable");
        child.add("Crystals: non-recyclable");
        childItems.add(child);

        // Light bulbs
        child = new ArrayList<String>();
        child.add("LED light bulb");
        child.add("CFL light bulb");
        child.add("Incandescent light bulb: non-recyclable");
        childItems.add(child);

        //Batteries
        child = new ArrayList<String>();
        child.add("Household battery");
        child.add("Button battery");
        child.add("Car battery");
        childItems.add(child);

        // Electronics
        child = new ArrayList<String>();
        child.add("Landline phone");
        child.add("Cell phone");
        child.add("CPU");
        child.add("Monitor");
        child.add("Printer");
        child.add("Keyboard");
        child.add("Laptop");
        child.add("Television");
        child.add("Calculator");
        child.add("Photocopier");
        child.add("Cordless tool");
        child.add("Corded tool");
        child.add("Microwave: non-recyclable");
        child.add("Fridge: non-recyclable");
        child.add("Fire/Smoke detector & Alarms: non-recyclable");
        child.add("Digital clock");
        child.add("Stereo");
        child.add("Thermometer: non-recyclable");
        childItems.add(child);

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
