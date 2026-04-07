package com.example.sit305_4_1p;

import android.graphics.Color;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String name, location, category;
    private int id;
    private String color;
    private LocalDate date;
    private LocalTime startTime, endTime;

    public Event(int id, String name, LocalTime startTime, LocalTime endTime, LocalDate date, String location, String category, String color) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.location = location;
        this.category = category;
        this.color = color;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getLocation() {
        return location;
    }
    public String getCategory() {
        return category;
    }
    public String getColor() {
        return color;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
