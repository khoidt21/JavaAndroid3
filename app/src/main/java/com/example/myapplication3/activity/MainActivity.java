package com.example.myapplication3.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication3.R;
import java.util.ArrayList;
import model.Alarm;

public class MainActivity extends AppCompatActivity{

    private static final int REQUEST_CODE = 0 ;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Alarm> listAlarm = new ArrayList<>();
    ToggleButton toggleButton;

    public static final int REQUEST_ALARM = 2048;
    AlarmManager alarmManager;

    int hour;
    String title = "Alarm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set title cho action bar
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }
    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        // khoi tao alarmManager
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);


        // lay id button
        toggleButton = (ToggleButton) findViewById(R.id.tglAlarm);

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
                        // lay duoc thong tin alarm
                        Alarm alarmResult = (Alarm) getBundle.getSerializable("alarmresult");

                        // add thong tin cua alarm vao listAlram
                        listAlarm.add(alarmResult);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        AlarmAdapter adapter = new AlarmAdapter(listAlarm);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        }

    }


}