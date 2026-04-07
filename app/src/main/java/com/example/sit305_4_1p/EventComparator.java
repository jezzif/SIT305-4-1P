package com.example.sit305_4_1p;

import java.time.LocalDate;
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        int order = e1.getDate().compareTo(e2.getDate());
        if (order == 0) {
            return e1.getStartTime().compareTo(e2.getStartTime());
        }
        return order;
    }
}
