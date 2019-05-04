package com.example.myapplication3.activity;

import android.app.Activity;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.myapplication3.R;
import java.util.ArrayList;

public class AddAlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    EditText title;
    Button btnAddAlarm;
    int hour;
    int minute;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alarm");
        btnAddAlarm = (Button) findViewById(R.id.btnAddAlarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        title = (EditText) findViewById(R.id.editTitle);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                Intent intent = new Intent(AddAlarmActivity.this,MainActivity.class);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                intent.putExtra("title",title.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

}
