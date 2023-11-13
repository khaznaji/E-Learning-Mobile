/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appsnipp.education.complaint;

import java.util.List;

@Dao
public interface complaintDao {



        @Insert
        void insertTodo(complaint complaint);

        @Query("SELECT * FROM complaint_table")
        List<complaint> getAllTodos();

        @Query("SELECT * FROM complaint_table WHERE complaint_uid LIKE :uid")
        complaint findTodoById(int uid);

        @Delete
        void deleteTodo(complaint complaint);

        @Update
        void updateTodo(complaint complaint);

        @Insert
        void insertMultipleTodos(List<complaint> todoList);

//        @Query("SELECT * FROM complaint_table WHERE todo_completed LIKE 1")
//        List<Todo> getAllCompletedTodos();

}
