 /*
         * Copyright 2016 Farbod Salamat-Zadeh
         *
         * Licensed under the Apache License, Version 2.0 (the "License");
         * you may not use this file except in compliance with the License.
         * You may obtain a copy of the License at
         *
         *     http://www.apache.org/licenses/LICENSE-2.0
         *
         * Unless required by applicable law or agreed to in writing, software
         * distributed under the License is distributed on an "AS IS" BASIS,
         * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
         * See the License for the specific language governing permissions and
         * limitations under the License.
         */

        package com.example.myapplication3.activity;


        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.myapplication3.R;

        import java.sql.Time;
        import java.text.Format;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.List;

        import model.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Alarm> alarms;

    public AlarmAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView hourShow = viewHolder.itemView.findViewById(R.id.txtHour);
        TextView amPm = viewHolder.itemView.findViewById(R.id.txtAmPm);

        hourShow.setText(alarms.get(i).getHour()+":" + alarms.get(i).getMinute());
        String am_pm = (alarms.get(i).getHour() < 12) ? "AM" : "PM";
        amPm.setText(am_pm);

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }


}