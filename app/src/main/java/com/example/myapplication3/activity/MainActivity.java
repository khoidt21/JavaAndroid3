package com.example.myapplication3.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.myapplication3.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Alarm;

public class MainActivity extends AppCompatActivity implements AlarmListener{

    private static final int REQUEST_CODE = 0 ;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Alarm> listAlarm = new ArrayList<>();
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
        // xu ly border recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources()));
        // khoi tao alarmManager
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
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
            case R.id.add_setting:
                Intent settingIntent = new Intent(MainActivity.this, SettingMusicActivity.class);
                MainActivity.this.startActivityForResult(settingIntent,REQUEST_ALARM);
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

                        // add thong tin cua alarm vao listAlarm
                        listAlarm.add(alarmResult);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        AlarmAdapter adapter = new AlarmAdapter(listAlarm,this);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

        }

    }


    @Override
    public void startAlarm(Alarm alarm, int requestCode) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour());
        calendar.set(Calendar.MINUTE, alarm.getMinute());
        Intent intent_alarm_receiver = new Intent(MainActivity.this,AlarmReceiver.class);
        intent_alarm_receiver.putExtra("music_flag",true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent_alarm_receiver,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),500,pendingIntent);

    }

    @Override
    public void cancelAlarm(Alarm alarm, int requestCode) {
        Intent intent_alarm_receiver = new Intent(MainActivity.this,AlarmReceiver.class);
        intent_alarm_receiver.putExtra("music_flag",false);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent_alarm_receiver,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        sendBroadcast(intent_alarm_receiver);
        Toast.makeText(this, "Alarm stopped!", Toast.LENGTH_SHORT).show();
    }
}