package com.appsnipp.education.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appsnipp.education.Entity.Post;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insert(Post post);

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);

    @Query("SELECT * FROM posts WHERE id = :id")
    Post getPostById(int id);



    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();
}
