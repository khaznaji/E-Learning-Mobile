/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Forum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.Post;
import com.appsnipp.education.R;

import java.util.List;

public class Forum  extends AppCompatActivity {
    Button buttonSubmit;
    EditText title,content;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);

        database=AppDataBase.getInstance(this);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        title=findViewById(R.id.editTextTitle);
        content=findViewById(R.id.editTextContent);

        buttonSubmit.setOnClickListener(v -> {
            try {
                Post post = new Post(title.getText().toString(), content.getText().toString());
                database.postDao().insert(post);
                startActivity(new Intent(Forum.this, ListForum.class));
                finish();
                Log.d("addPost", "Post inserted: " + post);

                List<Post> lu = database.postDao().getAllPosts();
                for (Post userList : lu) {
                    Log.d("addPost", "User List: " + userList);
                }
            } catch (Exception e) {
                Log.e("addPost", "Error inserting post: " + e.getMessage());
            }
        });
    }
}