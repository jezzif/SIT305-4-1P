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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventListFragment extends Fragment implements OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Event> eventList = new ArrayList<>();

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