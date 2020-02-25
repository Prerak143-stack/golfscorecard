package com.example.patel.golfscorecard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private final Holes[] holes;


    ListAdapter(Context context, Holes[] holes){
        this.context = context;
        this.holes = holes;
    }

    @Override
    public int getCount() {
        return holes.length;
    }

    @Override
    public Object getItem(int position) {
        return holes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list, null);

            holder = new ViewHolder();
            holder.labelTextView = convertView.findViewById(R.id.labelTextView);
            holder.scoreTextView = convertView.findViewById(R.id.scoreTextView);
            holder.minusButton = convertView.findViewById(R.id.minusButton);
            holder.plusButton = convertView.findViewById(R.id.plusButton);

            convertView.setTag(holder);

        }
        else {

            holder = (ViewHolder) convertView.getTag();

        }


        holder.labelTextView.setText(holes[position].getLabel());
        holder.scoreTextView.setText(holes[position].getStrokeCount() + "");
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = holes[position].getStrokeCount() - 1;
                if(score < 0) score = 0;

                holes[position].setStrokeCount(score);
                holder.scoreTextView.setText(score + "");


            }
        });
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = holes[position].getStrokeCount() + 1;
                holes[position].setStrokeCount(score);
                holder.scoreTextView.setText(score + "");
            }
        });


        return convertView;
    }

    public class ViewHolder {

        TextView labelTextView;
        TextView scoreTextView;
        Button minusButton;
        Button plusButton;


    }
}
