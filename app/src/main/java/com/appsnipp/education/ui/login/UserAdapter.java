/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewUserName.setText("First Name: " + user.getFirstname());
        holder.textelastname.setText("Last Name: " + user.getLastname());
        holder.textemail.setText("Email: " + user.getEmail());

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
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textelastname = itemView.findViewById(R.id.textelastname);
            textemail = itemView.findViewById(R.id.textemail);

            // Initialisez d'autres TextView ici
        }
    }}