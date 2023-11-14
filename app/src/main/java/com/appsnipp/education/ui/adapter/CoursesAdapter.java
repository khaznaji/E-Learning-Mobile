/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.R;
import com.appsnipp.education.Room.Courses;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter< CoursesAdapter.MyViewHolder> {
    private Context context;
    private List<Courses> coursesList;

    private AdapterListener adapterListener;

    public CoursesAdapter(Context context,AdapterListener listener) {
        this.context = context;
        coursesList=new ArrayList<>();
        this.adapterListener=listener;
    }

    public void addCourses (Courses courses) {
        coursesList.add(courses);
        notifyDataSetChanged();
    }







    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcourses,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Courses courses= coursesList.get(position);
        holder.Category.setText("First Name: " + courses.getCategory());
        holder.Text_course_id.setText("First Name: " + courses.getTitle());
        holder.Description.setText("First Name: " + courses.getDescription());
        holder.Level.setText("First Name: " + courses.getLevel());
        holder.Duration.setText("First Name: " + courses.getDuration());


        holder.modifier.setOnClickListener(view -> adapterListener.OnUpdate( courses));

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

private TextView Category, Text_course_id,Description,Level,Duration;
private Button modifier;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Category=itemView.findViewById(R.id.category);
            Text_course_id=itemView.findViewById(R.id.text_course_id);
            Description=itemView.findViewById(R.id.description);
            Level=itemView.findViewById(R.id.level);
            Duration=itemView.findViewById(R.id.duration);
            modifier=itemView.findViewById(R.id.modifier);


        }
    }
}
