package com.example.sit305_4_1p;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;
import java.util.concurrent.Executors;

public class EventsRepository {
    private EventDao eventDao;

    public EventsRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        eventDao = db.eventDao();
    }

    public LiveData<List<Event>> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public void insert(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long id = eventDao.insert(event);
        });
    }

    public void delete(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> {
            eventDao.delete(event.getId());
        });
    }

    public void update(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> {
            eventDao.update(event.getId(),
                    event.getName(),
                    event.getStartTime(),
                    event.getEndTime(),
                    event.getDate(),
                    event.getLocation(),
                    event.getCategory(),
                    event.getColor());
        });
    }

    public void deleteAll() {
        Executors.newSingleThreadExecutor().execute(() -> {
            eventDao.deleteAll();
        });
    }
}
