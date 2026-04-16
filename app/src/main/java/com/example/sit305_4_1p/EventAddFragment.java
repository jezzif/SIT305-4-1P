package com.example.sit305_4_1p;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EventAddFragment extends Fragment {
    private int[] colors = {R.drawable.black, R.drawable.red, R.drawable.orange, R.drawable.green, R.drawable.blue, R.drawable.purple};
    private boolean firstTime = false;
    private String name, category, location;
    private String color;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean edit = false;
    private Event openedEvent;

    public EventAddFragment() {
        // Required empty public constructor
    }

    public static EventAddFragment newInstance() {
        EventAddFragment fragment = new EventAddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            openedEvent = (Event) getArguments().getSerializable("event");
            edit = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = requireContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_add, container, false);

        EventViewModel viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

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
        Button deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
        deleteBtn.setVisibility(view.INVISIBLE);

        // Toasts
        int duration = Toast.LENGTH_SHORT;
        Toast deleteToast = Toast.makeText(context, "Event deleted", duration);;
        Toast strValToast = Toast.makeText(context, "Please fill in all fields", duration);
        Toast dateValToast = Toast.makeText(context, "Date cannot be in the past", duration);

        // Times and Dates
        LocalTime currentTime = LocalTime.now();
        startTimeBtn.setText(currentTime.format(hmf));
        endTimeBtn.setText(currentTime.plusHours(1).format(hmf));
        LocalDate currentDate = LocalDate.now();
        dateBtn.setText(currentDate.format(dmf));

        startTime = LocalTime.of(currentTime.getHour(), currentTime.getMinute());
        endTime = startTime.plusHours(1);
        date = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());

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

        if (edit) {
            nameInput.setText(openedEvent.getName());
            categoryInput.setText(openedEvent.getCategory());
            locationInput.setText(openedEvent.getLocation());
            startTimeBtn.setText(openedEvent.getStartTime().format(hmf));
            endTimeBtn.setText(openedEvent.getEndTime().format(hmf));
            dateBtn.setText(openedEvent.getDate().format(dmf));
            name = openedEvent.getName();
            category = openedEvent.getCategory();
            location = openedEvent.getLocation();
            startTime = openedEvent.getStartTime();
            endTime = openedEvent.getEndTime();
            date = openedEvent.getDate();
            color = openedEvent.getColor();
            deleteBtn.setVisibility(view.VISIBLE);
            Log.i("edit", name + location + category);
        }

        CustomAdapter customAdapter = new CustomAdapter(context, colors);
        colorSpinner.setAdapter(customAdapter);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                LocalTime time = LocalTime.of(hourOfDay, minute);
                startTimeBtn.setText(time.format(hmf));
                startTime = time;
                if (!firstTime) {
                    endTimeBtn.setText(time.plusHours(1).format(hmf));
                    endTime = time.plusHours(1);
                }
            }
        }, currentTime.getHour(), currentTime.getMinute(), true);
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                LocalTime time = LocalTime.of(hourOfDay, minute);
                endTimeBtn.setText(time.format(hmf));
                endTime = time;
                firstTime = true;
            }
        }, currentTime.getHour(), currentTime.getMinute(), true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                dateBtn.setText(selectedDate.format(dmf));
                date = selectedDate;
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

        Fragment eventListFrag = new EventListFragment();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("isEdit", String.valueOf(edit));
                name = String.valueOf(nameInput.getText());
                category = String.valueOf(categoryInput.getText());
                location = String.valueOf(locationInput.getText());
                Log.i("myData", name + startTime + endTime);
                Event newEvent = new Event(name, startTime, endTime, date, location, category, color);
                boolean next = true;
                if (Objects.equals(name, "") || Objects.equals(category, "") || Objects.equals(location, "")) {
                    strValToast.show();
                    next = false;
                }
                else if (date.compareTo(currentDate) < 0) {
                    dateValToast.show();
                    next = false;
                }
                if (next) {
                    if (edit) {
                        newEvent.setId(openedEvent.getId());
                        viewModel.update(newEvent);
                    }
                    else {
                        viewModel.insert(newEvent);
                    }
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, eventListFrag)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(openedEvent);
                deleteToast.show();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, eventListFrag)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}