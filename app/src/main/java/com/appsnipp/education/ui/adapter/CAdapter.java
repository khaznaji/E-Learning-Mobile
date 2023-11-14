/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.R;
import com.appsnipp.education.Room.Courses;
import com.appsnipp.education.ui.Courses.ListCourses;

import java.util.List;

public class CAdapter extends RecyclerView.Adapter< CAdapter.MyViewHolder> {

    private List<Courses> kitaList;

    public CAdapter(List<Courses> kitaList) {
        this.kitaList = kitaList;
    }
    public void setKitaList(List<Courses> kitaList) {
        this.kitaList = kitaList;    }


    @NonNull
    @Override
    public CAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CAdapter.MyViewHolder holder, int position) {
        Courses courses = kitaList.get(position);

        // Mettez à jour les TextView avec les données de l'utilisateur
        holder.Category.setText("First Name: " + courses.getCategory());
        holder.Description.setText("Last Name: " + courses.getDescription());
        holder.Duration.setText("Last Name: " + courses.getDuration());
        holder.Level.setText("Last Name: " + courses.getDuration());
        holder.Title.setText("Last Name: " + courses.getTitle());


    }

    @Override
    public int getItemCount() {
        return kitaList.size();



    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView Category, Title,Description,Level,Duration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Category=itemView.findViewById(R.id.category);
            Title=itemView.findViewById(R.id.title);
            Description=itemView.findViewById(R.id.Description);
            Level=itemView.findViewById(R.id.level);
            Duration=itemView.findViewById(R.id.duration);

        }
    }
}
