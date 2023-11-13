/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Dao;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import androidx.room.Dao;

import com.appsnipp.education.Entity.Courses;

import java.util.List;

@Dao
public interface CoursesDao {

    @Insert
    void AddCourses(Courses courses) ;

    @Update
    void UpdateCourses (Courses courses);
    @Delete
    void deleteTodo(Courses courses);
    @Query(" delete  from Courses where id=:id ")
    void delete (int id );

    @Query(" Select *  from Courses  ")
    List<Courses> ListCourses ( );



}