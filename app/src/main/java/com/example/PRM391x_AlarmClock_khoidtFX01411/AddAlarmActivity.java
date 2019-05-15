package com.example.PRM391x_AlarmClock_khoidtFX01411;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import com.example.PRM391x_AlarmClock_khoidtFX01411.R;

public class AddAlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    String event;
    Button btnAddAlarm;
    int hour;
    int minute;
    Toolbar toolbar;
    EditText editText;

    boolean buttonToggleBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alarm");
        btnAddAlarm = (Button) findViewById(R.id.btnAddAlarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        editText = (EditText) findViewById(R.id.editEvent);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                event = editText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("HOUR",hour);
                intent.putExtra("MINUTE",minute);
                intent.putExtra("EVENT",event);
                setResult(1019, intent);
                finish();
            }
        });
    }
    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

}
