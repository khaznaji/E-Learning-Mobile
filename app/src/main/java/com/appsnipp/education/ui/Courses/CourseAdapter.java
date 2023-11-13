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
import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;
import com.appsnipp.education.ui.Complaints.ListComplaints;
import com.appsnipp.education.ui.Events.EventAdapter;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.UserViewHolder> {

    private List<Courses> userList;

    public CourseAdapter(List<Courses> userList) {
        this.userList = userList;
    }
    public void setUserList(List<Courses> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public CourseAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course, parent, false);
        return new CourseAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.UserViewHolder holder, int position) {
        Courses user = userList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewTitle.setText("First Name: " + user.getDescription());
        holder.texteDescription.setText("Last Name: " + user.getCategory());
        holder.btnDelete.setOnClickListener(v -> {
            if (v.getContext() instanceof ListCourses) {
                ((ListCourses) v.getContext()).deleteComplaint(user);
            }
        });
        // Ajoutez d'autres mises à jour pour afficher d'autres détails de l'utilisateur

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView texteDescription;
        Button btnDelete;

        // Ajoutez d'autres TextView pour d'autres détails de l'utilisateur

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            texteDescription = itemView.findViewById(R.id.texteDescription);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            // Initialisez d'autres TextView ici
        }
    }}
