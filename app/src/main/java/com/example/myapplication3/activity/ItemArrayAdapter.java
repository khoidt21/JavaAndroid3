package com.example.myapplication3.activity;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication3.R;

import model.Alarm;
import java.util.ArrayList;

class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {

    private int listItemLayout;
    private ArrayList<Alarm> listItem;

    public ItemArrayAdapter(int layoutId, ArrayList<Alarm> listItem) {
        listItemLayout = layoutId;
        this.listItem = listItem;
    }

    @Override
    public int getItemCount() {
        return listItem == null ? 0 : listItem.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.hour;
        item.setText(listItem.get(listPosition).getHour());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView hour;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            hour = (TextView) itemView.findViewById(R.id.txtHour);

        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + hour.getText());
        }
    }
}
