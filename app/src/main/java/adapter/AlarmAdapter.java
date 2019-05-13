
package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.myapplication3.R;
import com.example.myapplication3.activity.MainActivity;

import dbhelper.AlarmDbHelper;
import listener.AlarmListener;

import java.util.List;
import model.Alarm;

    public class AlarmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<Alarm> alarms;
    AlarmListener alarmListener;
    AlarmDbHelper alarmDbHelper;
    private Context context;

//    AlarmDbHelper alarmDbHelper = null;


    public AlarmAdapter(Context context,List<Alarm> alarms, AlarmListener l) {
        this.alarms = alarms;
        this.context = context;
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

        //System.out.println("================== alarms"+ alarms.size());

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

        final boolean toggle = alarms.get(i).isToggleOnOff();

       // System.out.println("lay tu sqlite ra " + toggle);
        if(toggle == false){
            toggleButton.setChecked(false);
        }
        else if(toggle == true) {
            toggleButton.setChecked(true);
        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    alarm.setToggleOnOff(true);
                    alarmDbHelper = new AlarmDbHelper(context);
                    alarmListener.startAlarm(alarm,i);
                    alarmDbHelper.updateAlarm(alarm);
                }else{

                    alarm.setToggleOnOff(false);
                    alarmListener.cancelAlarm(alarm,i);
                    alarmDbHelper = new AlarmDbHelper(context);
                    alarmDbHelper.updateAlarm(alarm);

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return alarms.size();
    }
}