/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnItemClickListener onItemClickListener;

    public UserAdapter(List<User> userList, OnItemClickListener onItemClickListener) {
        this.userList = userList;
        this.onItemClickListener = onItemClickListener;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
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

        // Set click listener for the Delete button
        holder.btnDelete.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textelastname;
        TextView textemail;
        Button btnDelete;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textelastname = itemView.findViewById(R.id.textelastname);
            textemail = itemView.findViewById(R.id.textemail);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
