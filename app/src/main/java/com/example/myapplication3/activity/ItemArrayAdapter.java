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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication3.R;

import java.util.ArrayList;

import model.Alarm;

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ExampleViewHolder> {

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        TextView tvHour,tvTitle ;

        ExampleViewHolder(View itemView) {
            super(itemView);
            tvHour = (TextView) itemView.findViewById(R.id.txtHour);
            tvTitle = (TextView) itemView.findViewById(R.id.editTitle);
        }
    }

    private ArrayList<Alarm> mCustomObjects;

    public ItemArrayAdapter(ArrayList<Alarm> arrayList) {
        mCustomObjects = arrayList;
    }

    @Override
    public int getItemCount() {
        return mCustomObjects.size();
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Alarm object = mCustomObjects.get(position);

        // My example assumes CustomClass objects have getFirstText() and getSecondText() methods
        int hour = object.getHour();
        System.out.println(hour+"---------------------------------");
        int minute = object.getMinute();
        String title = object.getTitle();


        holder.tvHour.setText(hour);
        holder.tvTitle.setText(title);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}