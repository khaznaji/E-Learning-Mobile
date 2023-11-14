/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appsnipp.education.Dao.PostDao;
import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.Entity.Post;
import com.appsnipp.education.Entity.User;

@Database(entities = {User.class, Post.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance ;
    // a tt les DAO's
    public abstract UserDao userDAO();
    public abstract PostDao postDao();
    public static AppDataBase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "education")
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;
    }
}