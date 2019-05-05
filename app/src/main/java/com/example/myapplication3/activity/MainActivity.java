package com.example.myapplication3.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication3.R;
import java.util.ArrayList;
import model.Alarm;

public class MainActivity extends AppCompatActivity{

    private static final int REQUEST_CODE = 0 ;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Alarm> listAlram = new ArrayList<>();
    Alarm alarm = new Alarm();

    public static final int REQUEST_ALARM = 2048;

    int hour;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set title cho action bar
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alarm");

    }
    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_on_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_alarm :
                Intent myIntent = new Intent(MainActivity.this, AddAlarmActivity.class);
                MainActivity.this.startActivityForResult(myIntent, REQUEST_ALARM);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ALARM) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle getBundle = data.getExtras();
                    if (getBundle != null) {
                        Alarm alarmResult = (Alarm) getBundle.getSerializable("alarmresult");

                        System.out.println("============" +alarmResult);


                        // Setting up the RecyclerView
                        listAlram.add(alarmResult);
                        System.out.println("=============== +++++++++++++" + listAlram.size());

                        assert recyclerView != null;
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                        // Getting your ArrayList - this will be up to you
                      //  ArrayList<Alarm> yourObjects = getMyObjects();


                        // Standard RecyclerView implementation

                        ItemArrayAdapter adapter = new ItemArrayAdapter(listAlram);
                        recyclerView.setAdapter(adapter);

                        //Toast.makeText(this, alarmResult.toString(), Toast.LENGTH_SHORT).show();
                       // System.out.println("===================" + alarmResult.getHour());

                    }
                }
            }
        }

    }


}