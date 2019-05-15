
package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.PRM391x_AlarmClock_khoidtFX01411.AddAlarmActivity;
import com.example.PRM391x_AlarmClock_khoidtFX01411.R;

import dbhelper.AlarmDbHelper;
import listener.AlarmListener;

import java.util.List;
import model.Alarm;

    public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHoder>  {

    List<Alarm> alarms;
    AlarmListener alarmListener;
    AlarmDbHelper alarmDbHelper;
    private Context context;

    public AlarmAdapter(Context context,List<Alarm> alarms, AlarmListener l) {
        this.alarms = alarms;
        this.context = context;
        this.alarmListener = l;
    }

    public AlarmAdapter() {
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.alarm_item, viewGroup, false);
        return new ViewHoder(view);
    }

        @Override
        public void onBindViewHolder(@NonNull ViewHoder viewHoder, final int i) {
            TextView hourShow = viewHoder.itemView.findViewById(R.id.txtHour);
            TextView amPm = viewHoder.itemView.findViewById(R.id.txtAmPm);
            TextView event = viewHoder.itemView.findViewById(R.id.txtEvent);
            final ToggleButton toggleButton = viewHoder.itemView.findViewById(R.id.tglAlarm);

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


    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {

        public ViewHoder(View view){
             super(view);
             view.setOnCreateContextMenuListener(this);
        }


        @Override
        public boolean onMenuItemClick(MenuItem item) {

            int position = getLayoutPosition();
            alarmListener.onMenuAction(item,position);
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_main,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}