package com.example.myapplication3.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.Alarm;

public class AlarmDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarmManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "alarms";

    //private static final String KEY_ID = "id";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_AM_PM = "ampm";
    private static final String KEY_EVENT = "event";
    private static final String KEY_STATUS = "status";

    // private static final String KEY_SONG = "idsong";


    public AlarmDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_alarms_table = String.format("CREATE TABLE %s(%s INTEGER,%s INTEGER,%s TEXT,%s TEXT,%s BOOLEAN)",
                    TABLE_NAME,KEY_HOUR,KEY_MINUTE,KEY_AM_PM,KEY_EVENT,KEY_STATUS
                );
        db.execSQL(create_alarms_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_alarms_table = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        db.execSQL(drop_alarms_table);
        onCreate(db);
    }
    public void addAlarm(Alarm alarm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values.put(KEY_ID,alarm.getId());

        values.put(KEY_HOUR,alarm.getHour());
        values.put(KEY_MINUTE,alarm.getMinute());
        values.put(KEY_AM_PM,alarm.getAmpm());
        values.put(KEY_EVENT,alarm.getEvent());
        values.put(KEY_STATUS,alarm.isStatus());

        // values.put(KEY_SONG,alarm.getIdsong());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<Alarm> getAlarms(){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] { KEY_HOUR, KEY_MINUTE,KEY_AM_PM,KEY_EVENT,KEY_STATUS };

        Cursor cursor = db.query(TABLE_NAME, columns, null,
                null, null, null, null);

       // Alarm alarm = new Alarm(cursor.getInt(0),cursor.getInt(1),
                     //  cursor.getString(2),cursor.getString(3),
                // cursor.getInt(4) > 0);
        //return alarm;
        ArrayList<Alarm> alarmList = new ArrayList<>();
        Alarm alarm = new Alarm();
       if(cursor.moveToFirst()){
           do{
               int hour = cursor.getInt(0);
               int minute = cursor.getInt(1);
               String ampm = cursor.getString(2);
               String event = cursor.getString(3);
               boolean status = cursor.getInt(4) > 0;

               alarm.setHour(hour);
               alarm.setMinute(minute);
               alarm.setAmpm(ampm);
               alarm.setEvent(event);
               alarm.setStatus(status);

               alarmList.add(alarm);

           }while (cursor.moveToNext());
       }
       return alarmList;
    }


//    public void updateAlarm(Alarm alarm){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_HOUR,alarm.getHour());
//        values.put(KEY_MINUTE,alarm.getMinute());
//        values.put(KEY_AM_PM,alarm.getAmpm());
//        values.put(KEY_EVENT,alarm.getEvent());
//        values.put(KEY_STATUS,alarm.isStatus());
//
//        //values.put(KEY_SONG,alarm.getIdsong());
//
//        db.update(TABLE_NAME,values,KEY_ID + "=?",new String[]{String.valueOf(alarm.getId())});
//        db.close();
//    }

}
