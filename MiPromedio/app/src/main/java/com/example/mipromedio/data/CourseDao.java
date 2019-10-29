package com.example.mipromedio.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(Course course);

    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourses();

    @Query("DELETE FROM course")
    void deleteAll();

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course LIMIT 1")
    Course[] getAnyCourse();
}
