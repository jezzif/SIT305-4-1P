package com.example.sit305_4_1p;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    private boolean firstTime = false;
    private String color;

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

        DateTimeFormatter hmf = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dmf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Views
        EditText nameInput = (EditText) view.findViewById(R.id.nameInput);
        Spinner colorSpinner = (Spinner) view.findViewById(R.id.colorSpinner);
        Button startTimeBtn = (Button) view.findViewById(R.id.startTimeBtn);
        Button endTimeBtn = (Button) view.findViewById(R.id.endTimeBtn);
        Button dateBtn = (Button) view.findViewById(R.id.dateBtn);
        EditText categoryInput = (EditText) view.findViewById(R.id.categoryInput);
        EditText locationInput = (EditText) view.findViewById(R.id.locationInput);
        Button saveBtn = (Button) view.findViewById(R.id.saveBtn);

        // Times and Dates
        LocalTime currentTime = LocalTime.now();
        startTimeBtn.setText(currentTime.format(hmf));
        endTimeBtn.setText(currentTime.plusHours(1).format(hmf));
        LocalDate currentDate = LocalDate.now();
        dateBtn.setText(currentDate.format(dmf));

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        color = "black";
                        break;
                    case 1:
                        color = "red";
                        break;
                    case 2:
                        color = "orange";
                        break;
                    case 3:
                        color = "green";
                        break;
                    case 4:
                        color = "blue";
                        break;
                    case 5:
                        color = "purple";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        CustomAdapter customAdapter = new CustomAdapter(context, colors);
        colorSpinner.setAdapter(customAdapter);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                LocalTime time = LocalTime.of(hourOfDay, minute);
                startTimeBtn.setText(time.format(hmf));
                if (!firstTime) {
                    endTimeBtn.setText(time.plusHours(1).format(hmf));
                }
            }
        }, currentTime.getHour(), currentTime.getMinute(), true);
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                LocalTime time = LocalTime.of(hourOfDay, minute);
                endTimeBtn.setText(time.format(hmf));
                firstTime = true;
            }
        }, currentTime.getHour(), currentTime.getMinute(), true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
                dateBtn.setText(date.format(dmf));
            }
        }, currentDate.getYear(), (currentDate.getMonthValue() - 1), currentDate.getDayOfMonth());



        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePickerDialog.show();
            }
        });
        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTimePickerDialog.show();
            }
        });
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        String name = String.valueOf(nameInput.getText());
        String category = String.valueOf(categoryInput.getText());
        String location = String.valueOf(locationInput.getText());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}