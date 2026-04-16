package com.example.sit305_4_1p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Event> eventList = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;


    public RecyclerViewAdapter(OnItemClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }
    public void setEvents(List<Event> eventList) {
        this.eventList.clear();
        this.eventList.addAll(eventList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        Context context = holder.itemView.getContext();

        holder.rowNameTextView.setText(event.getName());
        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();
        String timeText = "";
        if (startTime != null && endTime != null) {
            timeText = startTime.toString() + " - " + endTime.toString();
        }
        holder.rowTimeTextView.setText(timeText);
        if (event.getDate() != null) {
            holder.rowDateTextView.setText(event.getDate().toString());
        } else {
            holder.rowDateTextView.setText("");
        }
        holder.rowLocationTextView.setText(event.getLocation());
        holder.rowCategoryTextView.setText(event.getCategory());
        String color = event.getColor();
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

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (eventList != null) {
            return eventList.size();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
