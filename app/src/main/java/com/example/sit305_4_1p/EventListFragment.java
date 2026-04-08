package com.example.sit305_4_1p;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventListFragment newInstance(String param1, String param2) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private LocalTime[] createTimeObjList(int[][] rawList) {
        LocalTime[] newList = new LocalTime[rawList.length];
        for (int i = 0; i < rawList.length; i++) {
            newList[i] = (LocalTime.of(rawList[i][0], rawList[i][1]));
        }
        return newList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = requireContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        // Setup recyclerView
        List<Event> eventList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(eventList, context);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


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

        startTimeList = createTimeObjList(rawStartTimesList);
        endTimeList = createTimeObjList(rawEndTimeList);

        for (int i = 0; i < nameList.length; i++) {
            Event event = new Event(i, nameList[i], startTimeList[i], endTimeList[i], dateList[i], locationList[i], categoryList[i], colorList[i]);
            eventList.add(event);
        }
        eventList.sort(new EventComparator());

        return view;
    }
}