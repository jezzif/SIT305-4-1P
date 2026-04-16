package com.example.sit305_4_1p;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo
    private LocalDate date;
    @ColumnInfo
    private LocalTime startTime;
    @ColumnInfo
    private LocalTime endTime;

    public Event(String name, LocalTime startTime, LocalTime endTime, LocalDate date, String location, String category, String color) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.location = location;
        this.category = category;
        this.color = color;
    }
    // Getters
    public long getId() {
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
    public void setId(long id) {
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
