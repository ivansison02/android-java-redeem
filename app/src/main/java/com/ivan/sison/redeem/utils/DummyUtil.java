package com.ivan.sison.redeem.utils;

import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.data.entities.User;
import com.ivan.sison.redeem.data.objects.SavedEvents;

import java.util.ArrayList;

public class DummyUtil {

    public static Event getEvent() {
        return new Event(0, "Running Man PH 2020", "RM2020", "02-17-20T00:00:00", "02-18-20T00:00:00");
    }

    public static User getUser() {
        return new User(0, "", "", "", "", "", "", "", 0);
    }

    public static SavedEvents getSavedEvents() {
        return new SavedEvents(new ArrayList<Event>());
    }
}
