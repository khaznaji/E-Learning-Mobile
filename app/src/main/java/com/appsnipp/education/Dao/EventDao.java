package com.appsnipp.education.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appsnipp.education.Entity.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insertTodo(Event event);

    @Query("SELECT * FROM events_table")
    List<Event> getAllTodos();

    @Query("SELECT * FROM events_table WHERE idEvent LIKE :uid")
    Event findTodoById(int uid);

    @Delete
    void deleteTodo(Event event);

    @Update
    void updateTodo(Event event);

    @Insert
    void insertMultipleTodos(List<Event> todoList);

//        @Query("SELECT * FROM complaint_table WHERE todo_completed LIKE 1")
//        List<Todo> getAllCompletedTodos();

}