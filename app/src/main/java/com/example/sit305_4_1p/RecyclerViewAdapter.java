package com.example.sit305_4_1p;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Event> eventList;
    private Context context;

    public RecyclerViewAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowNameTextView.setText(eventList.get(position).getName());
        LocalTime startTime = eventList.get(position).getStartTime();
        LocalTime endTime = eventList.get(position).getEndTime();
        String timeText = startTime + " - " + endTime;
        holder.rowTimeTextView.setText(timeText);
        holder.rowDateTextView.setText(eventList.get(position).getDate().toString());
        holder.rowLocationTextView.setText(eventList.get(position).getLocation());
        holder.rowCategoryTextView.setText(eventList.get(position).getCategory());
        String color = eventList.get(position).getColor();
        int currentColor;
        switch (color) {
            case "red":
                currentColor = context.getColor(R.color.red);
                break;
            case "orange":
                currentColor = context.getColor(R.color.orange);
                break;
            case "green":
                currentColor = context.getColor(R.color.green);
                break;
            case "blue":
                currentColor = context.getColor(R.color.blue);
                break;
            case "purple":
                currentColor = context.getColor(R.color.purple);
                break;
            default:
                currentColor = context.getColor(R.color.black);
        }
        holder.cardView.setCardBackgroundColor(currentColor);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowNameTextView;
        TextView rowTimeTextView;
        CardView cardView;
        TextView rowDateTextView;
        TextView rowLocationTextView;
        TextView rowCategoryTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            rowNameTextView = itemView.findViewById(R.id.rowNameTextView);
            rowTimeTextView = itemView.findViewById(R.id.rowTimeTextView);
            rowDateTextView = itemView.findViewById(R.id.rowDateTextView);
            rowLocationTextView = itemView.findViewById(R.id.rowLocationTextView);
            rowCategoryTextView = itemView.findViewById(R.id.rowCategoryTextView);
        }
    }
}
