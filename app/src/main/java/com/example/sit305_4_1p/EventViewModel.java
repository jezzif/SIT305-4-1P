package com.example.sit305_4_1p;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventsRepository repository;
    private LiveData<List<Event>> allEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventsRepository(application);
        // LiveData itself is fine to be created here as Room returns a LiveData 
        // that performs the actual query on a background thread.
        allEvents = repository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void insert(Event event) {
        repository.insert(event);
    }

    public void delete(Event event) {
        repository.delete(event);
    }

    public void update(Event event) {
        repository.update(event);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
