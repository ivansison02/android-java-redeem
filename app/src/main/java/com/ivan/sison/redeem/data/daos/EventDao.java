package com.ivan.sison.redeem.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ivan.sison.redeem.data.entities.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    public void addEvent(Event event);

    @Update
    public void updateEvent(Event event);

    @Query("SELECT * FROM events")
    public List<Event> getAllEvents();
}