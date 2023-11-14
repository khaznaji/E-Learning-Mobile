/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Room;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import androidx.room.Dao;

import java.util.List;

@Dao
public interface CoursesDao {

    @Insert
    void AddCourses(Courses courses) ;

    @Update
    void UpdateCourses (Courses courses);

    @Query(" delete  from Courses where id=:id ")
    void delete (int id );

    @Query(" Select *  from Courses  ")
    List<Courses> ListCourses ( );



}
