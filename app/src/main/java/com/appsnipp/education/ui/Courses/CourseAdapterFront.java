/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.Courses;
import com.appsnipp.education.R;

import java.util.List;

public class CourseAdapterFront extends RecyclerView.Adapter<CourseAdapterFront.UserViewHolder> {

    private List<Courses> userList;

    public CourseAdapterFront(List<Courses> userList) {
        this.userList = userList;
    }

    public void setUserList(List<Courses> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public CourseAdapterFront.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_front, parent, false);
        return new CourseAdapterFront.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapterFront.UserViewHolder holder, int position) {
        Courses user = userList.get(position);

        // Update TextViews with user data
        holder.textViewTitle.setText("First Name: " + user.getDescription());
        holder.texteDescription.setText("Last Name: " + user.getCategory());

        // Add other updates to display other user details
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView texteDescription;


        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            texteDescription = itemView.findViewById(R.id.texteDescription);


            // Initialize other TextViews here
        }
    }
}
