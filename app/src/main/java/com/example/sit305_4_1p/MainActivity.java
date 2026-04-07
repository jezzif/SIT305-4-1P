package com.example.sit305_4_1p;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Event> eventList = new ArrayList<>();

    String[] nameList = {"Feed Pets", "Work", "Training", "Dinner Date", "Brush Teeth"};
    //String[] startTimeList = {"7:30","8:00","9:00","18:00","20:00"};
    int[][] rawStartTimesList = {{8,0},{9,0},{18,0},{20,0},{7,30}};
    //String[] endTimeList = {"7:45","8:15","5:00","19:30","22:30"};
    int[][] rawEndTimeList = {{8,15},{5,0},{19,30},{22,30},{7,45}};
    LocalTime[] startTimeList;
    LocalTime[] endTimeList;
    LocalDate now = LocalDate.of(2026, 4, 7);
    LocalDate[] dateList = {now, now, now, now, now};
    String[] locationList = {"Home","Work","Gym","Shops","Home"};
    String[] categoryList = {"Chores","Work","Social","Social","Chores"};

    String[] colorList = {"blue", "red", "green", "purple", "orange"};

    private LocalTime[] createTimeObjList(int[][] rawList) {
        LocalTime[] newList = new LocalTime[rawList.length];
        for (int i = 0; i < rawList.length; i++) {
            newList[i] = (LocalTime.of(rawList[i][0], rawList[i][1]));
        }
        return newList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(eventList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        startTimeList = createTimeObjList(rawStartTimesList);
        endTimeList = createTimeObjList(rawEndTimeList);

        for (int i = 0; i < nameList.length; i++) {
            Event event = new Event(i, nameList[i], startTimeList[i], endTimeList[i], dateList[i], locationList[i], categoryList[i], colorList[i]);
            eventList.add(event);
        }
        eventList.sort(new EventComparator());
    }
}