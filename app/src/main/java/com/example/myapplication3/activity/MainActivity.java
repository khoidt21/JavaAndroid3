package com.example.myapplication3.activity;

import android.content.Intent;
import android.content.res.Resources;
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
import com.example.myapplication3.R;
import java.util.ArrayList;
import model.Alarm;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Alarm> listAlram = new ArrayList<>();
    Alarm alarm = new Alarm();


    int hour;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Intent intent = getIntent();
//        title = intent.getStringExtra("title");
//
//
//        //String lName = intent.getStringExtra("lastName");
//
//        System.out.println(title + "========================================");

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
                MainActivity.this.startActivity(myIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int hour;
        int minute;
        String title;

        title = data.getStringExtra("title");
        System.out.println("=============" + title);



//        if(bundle != null){
//            hour = bundle.getInt("hour");
//            minute = bundle.getInt("minute");
//            title = bundle.getString("title");
//
//            System.out.println("================" +hour);
//            System.out.println(minute);
//
//
//            alarm.setHour(hour);
//            alarm.setMinute(minute);
//            alarm.setTitle(title);
//            alarmList.add(alarm);
//
//            System.out.println("=============" + alarmList.size());
//
//            ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(R.layout.alarm_item, alarmList);
//            recyclerView = (RecyclerView) findViewById(R.id.recycler);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.setAdapter(itemArrayAdapter);



    }

}
