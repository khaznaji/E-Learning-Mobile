/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Forum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appsnipp.education.Entity.Post;
import com.appsnipp.education.R;

import java.util.List;
public class ForumAdapter  extends RecyclerView.Adapter<ForumAdapter.UserViewHolder> {

    private List<Post> post;

    public ForumAdapter(List<Post> userList) {
        this.post = userList;
    }
    public void setUserList(List<Post> userList) {
        this.post = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemforum, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Post user = post.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.textViewUserName.setText("tilte: " + user.getTitle());
        holder.textelastname.setText("content: " + user.getContent());

        holder.btnDelete.setOnClickListener(v -> {
            if (v.getContext() instanceof ListForum) {
                ((ListForum) v.getContext()).deleteComplaint(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textelastname;
        TextView textemail;
        Button btnDelete;
        // Ajoutez d'autres TextView pour d'autres détails de l'utilisateur

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUserName = itemView.findViewById(R.id.texteDescription);
            textelastname = itemView.findViewById(R.id.textViewTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            // Initialisez d'autres TextView ici
        }

    }}


