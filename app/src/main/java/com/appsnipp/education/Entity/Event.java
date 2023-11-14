/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")

public class Event {
    @PrimaryKey(autoGenerate = true)
    private int idEvent;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")

    private String description;



    @ColumnInfo(name = "lieu")

    private String lieu;

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Event(int idEvent, String name, String description, String lieu) {
        this.idEvent = idEvent;
        this.name = name;
        this.description = description;
        this.lieu = lieu;
    }

    public Event() {
    }

    public Event(String name, String description, String lieu) {
        this.name = name;
        this.description = description;
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lieu='" + lieu + '\'' +
                '}';
    }
}
