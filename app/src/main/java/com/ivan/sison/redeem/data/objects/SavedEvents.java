package com.ivan.sison.redeem.data.objects;

import com.ivan.sison.redeem.data.entities.Event;

import java.util.ArrayList;

public class SavedEvents {

    private ArrayList<Event> events;

    public SavedEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
