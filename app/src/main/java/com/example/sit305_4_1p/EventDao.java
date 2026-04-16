package com.example.sit305_4_1p;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Dao
public interface EventDao {
    @Insert
    long insert(Event event);

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Query("DELETE FROM events WHERE id = :id")
    void delete(long id);

    @Query("UPDATE events SET name = :name, startTime = :startTime, endTime = :endTime, date = :date, location = :location, category = :category, color = :color WHERE id = :id")
    void update(long id, String name, LocalTime startTime, LocalTime endTime, LocalDate date, String location, String category, String color);


    @Query("DELETE FROM events")
    void deleteAll();
}

