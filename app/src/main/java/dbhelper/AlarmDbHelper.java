package dbhelper;

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
    private static final String KEY_ID = "id";
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
        String create_alarms_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER,%s INTEGER,%s TEXT,%s TEXT,%s INTEGER)",
                    TABLE_NAME,KEY_ID,KEY_HOUR,KEY_MINUTE,KEY_AM_PM,KEY_EVENT,KEY_STATUS
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

        values.put(KEY_HOUR,alarm.getHour());
        values.put(KEY_MINUTE,alarm.getMinute());
        values.put(KEY_AM_PM,alarm.getAmpm());
        values.put(KEY_EVENT,alarm.getEvent());
        values.put(KEY_STATUS,(alarm.isToggleOnOff() ? 1 : 0));

        // values.put(KEY_SONG,alarm.getIdsong());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<Alarm> getAlarms(){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] { KEY_ID,KEY_HOUR, KEY_MINUTE,KEY_AM_PM,KEY_EVENT,KEY_STATUS };

        Cursor cursor = db.query(TABLE_NAME, columns, null,
                null, null, null, KEY_ID
                        + " DESC",null);

        ArrayList<Alarm> alarmList = new ArrayList<>();
        if(cursor.moveToFirst()){
           do{
               Alarm alarm = new Alarm();
               int id = cursor.getInt(0);
               int hour = cursor.getInt(1);
               int minute = cursor.getInt(2);
               String ampm = cursor.getString(3);
               String event = cursor.getString(4);

               // boolean alarm luc dau bang false
               int status = cursor.getInt(5 );

               alarm.setHour(hour);
               alarm.setMinute(minute);
               alarm.setAmpm(ampm);
               alarm.setEvent(event);
               if(status == 1){

               alarm.setToggleOnOff(true);
               }
               else if(status == 0){alarm.setToggleOnOff(false);}
               alarmList.add(alarm);
           }while (cursor.moveToNext());
        }
        return alarmList;
    }

    public void updateAlarm(Alarm alarm){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOUR,alarm.getHour());
        values.put(KEY_MINUTE,alarm.getMinute());
        values.put(KEY_AM_PM,alarm.getAmpm());
        values.put(KEY_EVENT,alarm.getEvent());
        values.put(KEY_STATUS, (alarm.isToggleOnOff() ? 1 : 0));

        String[] args = new String[]{String.valueOf(alarm.getHour()) , String.valueOf(alarm.getMinute())};
        db.update(TABLE_NAME,values,"hour=? AND minute=?",args);
        db.close();
    }
    public void deleteAlarm(Alarm alarm){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = new String[]{String.valueOf(alarm.getHour()) , String.valueOf(alarm.getMinute())};
        db.delete(TABLE_NAME,"hour=? AND minute=?",args);
        db.close();
    }

}
