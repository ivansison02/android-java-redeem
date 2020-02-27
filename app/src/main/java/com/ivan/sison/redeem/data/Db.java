package com.ivan.sison.redeem.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ivan.sison.redeem.data.daos.EventDao;
import com.ivan.sison.redeem.data.daos.UserDao;
import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.data.entities.User;

@Database(entities = {User.class, Event.class}, version = 1)
public abstract class Db extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EventDao eventDao();
}
