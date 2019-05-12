
package com.example.myapplication3.activity;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.myapplication3.R;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Alarm;

    public class AlarmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<Alarm> alarms;
    AlarmListener alarmListener;

   // boolean checkToggle = false;

        public boolean checkToggle = false;

    public AlarmAdapter(List<Alarm> alarms, AlarmListener l) {
        this.alarms = alarms;
        this.alarmListener = l;
    }

    public AlarmAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.alarm_item, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        TextView hourShow = viewHolder.itemView.findViewById(R.id.txtHour);
        TextView amPm = viewHolder.itemView.findViewById(R.id.txtAmPm);
        TextView event = viewHolder.itemView.findViewById(R.id.txtEvent);
        final ToggleButton toggleButton = viewHolder.itemView.findViewById(R.id.tglAlarm);
        final Alarm alarm = alarms.get(i);

        // lay id cua alarm
       // int id = alarms.get(i).getId();


        int hour = alarms.get(i).getHour();
        int minute = alarms.get(i).getMinute();
        String eventAlarm = alarms.get(i).getEvent();
        
        if(alarms.get(i).getMinute() < 10){
            hourShow.setText(hour + ":" + 0 + minute);
        }
        else {
            hourShow.setText(hour + ":" + minute);
        }

        amPm.setText(alarms.get(i).getAmpm());
        event.setText(alarms.get(i).getEvent());

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    alarmListener.startAlarm(alarm,i);
                    //alarm.setStatus(true);
                    alarms.get(i).setStatus(true);

                }else{
                    alarmListener.cancelAlarm(alarm,i);
                    //alarm.setStatus(false);
                    alarms.get(i).setStatus(false);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}