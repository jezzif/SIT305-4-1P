package com.example.sit305_4_1p;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment implements OnItemClickListener {
    private List<Event> eventList = new ArrayList<>();

    public EventListFragment() {
        // Required empty public constructor
    }

    public static EventListFragment newInstance() {
        EventListFragment fragment = new EventListFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = requireContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        EventViewModel viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        // Setup recyclerView

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, context);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        viewModel.getAllEvents().observe(getViewLifecycleOwner(), events -> {
            if (events != null) {
                this.eventList = events;
                recyclerViewAdapter.setEvents(events);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        EventAddFragment eventAddFrag = new EventAddFragment();

        Event event = eventList.get(position);

        Bundle args = new Bundle();
        args.putSerializable("event", event);
        eventAddFrag.setArguments(args);


        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, eventAddFrag)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}