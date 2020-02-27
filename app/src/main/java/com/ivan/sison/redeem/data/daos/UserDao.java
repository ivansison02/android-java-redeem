package com.ivan.sison.redeem.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import com.ivan.sison.redeem.data.entities.User;

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Update
    public void updateUser(User user);


}
