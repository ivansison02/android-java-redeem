package com.ivan.sison.redeem.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String code;
    private String date;
    private String expiration;

    public Event(int id, String title, String code, String date, String expiration) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.date = date;
        this.expiration = expiration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
