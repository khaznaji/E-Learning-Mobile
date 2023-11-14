/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appsnipp.education.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    User getUser(String email, String password);
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);
    @Query("SELECT * FROM users WHERE id = :userId")
    User getUserById(int userId);
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    @Query("UPDATE users SET email = :newEmail, firstName = :newFirstName, lastName = :newLastName WHERE id = :userId")
    int updateUser(int userId, String newEmail, String newFirstName, String newLastName);
    @Query("SELECT * FROM users WHERE email = :email AND answer1 = :answer1 AND answer2 = :answer2")
    User getUserByAnswers(String email, String answer1, String answer2);

}