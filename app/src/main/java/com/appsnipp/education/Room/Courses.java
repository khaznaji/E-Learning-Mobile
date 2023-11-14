/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Courses implements Serializable {

@PrimaryKey( autoGenerate =true)
   private int id;
    private String title ;
    private String description ;
    private String level ;
    private String duration ;
    private String category ;

    public Courses() {
    }

    public Courses(int id, String title, String description, String level, String duration, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.level = level;
        this.duration = duration;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", level='" + level + '\'' +
                ", duration='" + duration + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
