/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.UserViewHolder> {

    private List<Event> userList;

    public EventAdapter(List<Event> userList) {
        this.userList = userList;
    }
    public void setUserList(List<Event> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public EventAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listevent, parent, false);
        return new EventAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.UserViewHolder holder, int position) {
        Event user = userList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewUserName.setText("First Name: " + user.getName());
        holder.textelastname.setText("Last Name: " + user.getDescription());

        // Ajoutez d'autres mises à jour pour afficher d'autres détails de l'utilisateur

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textelastname;
        TextView textemail;

        // Ajoutez d'autres TextView pour d'autres détails de l'utilisateur

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textelastname = itemView.findViewById(R.id.nameevent);

            textViewUserName = itemView.findViewById(R.id.descevent);

            // Initialisez d'autres TextView ici
        }
    }}