/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Complaints;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.complaint;
import com.appsnipp.education.R;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.UserViewHolder> {

    private List<complaint> userList;

    public ComplaintAdapter(List<complaint> userList) {
        this.userList = userList;
    }
    public void setUserList(List<complaint> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        complaint user = userList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewUserName.setText("First Name: " + user.getDescription());
        holder.textelastname.setText("Last Name: " + user.getTitle());

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
            textViewUserName = itemView.findViewById(R.id.texteDescription);
            textelastname = itemView.findViewById(R.id.textViewTitle);

            // Initialisez d'autres TextView ici
        }
    }}