package com.example.sit305_4_1p;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int[] colors = {R.drawable.black, R.drawable.red, R.drawable.orange, R.drawable.green, R.drawable.blue, R.drawable.purple};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventAddFragment newInstance(String param1, String param2) {
        EventAddFragment fragment = new EventAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_event_add, container, false);

        Spinner colorSpinner = (Spinner) view.findViewById(R.id.colorSpinner);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        CustomAdapter customAdapter = new CustomAdapter(context, colors);
        colorSpinner.setAdapter(customAdapter);

        return view;
    }
}