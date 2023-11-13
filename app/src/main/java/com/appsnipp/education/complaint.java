/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "complaint_table")
public class complaint {


    public complaint() {
    }

    public complaint(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "complaint{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "complaint_uid")
        private int uid;

    public int getUid() {
        return uid;
    }

    public complaint(int uid, String title, String description) {
        this.uid = uid;
        this.title = title;
        this.description = description;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public complaint(int uid) {
        this.uid = uid;
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

    @ColumnInfo(name = "title")
        private String title;

        @ColumnInfo(name = "description")
        private String description;


}
