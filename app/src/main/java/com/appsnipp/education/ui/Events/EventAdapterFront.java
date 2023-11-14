/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;
import com.appsnipp.education.ui.Courses.ListCourses;

import java.util.List;

public class EventAdapterFront extends RecyclerView.Adapter<EventAdapterFront.UserViewHolder> {

    private List<Event> userList;

    public EventAdapterFront(List<Event> userList) {
        this.userList = userList;
    }
    public void setUserList(List<Event> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event_front, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Event user = userList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewTitle.setText("First Name: " + user.getDescription());
        holder.texteDescription.setText("Last Name: " + user.getName());

        // Ajoutez d'autres mises à jour pour afficher d'autres détails de l'utilisateur

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView texteDescription;


        // Ajoutez d'autres TextView pour d'autres détails de l'utilisateur

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            texteDescription = itemView.findViewById(R.id.texteDescription);
            // Initialisez d'autres TextView ici
        }
    }}
