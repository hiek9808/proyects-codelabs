package com.example.mipromedio.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mipromedio.data.model.Grade;

import java.util.List;

@Dao
public interface GradeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Grade grade);

    @Query("SELECT * FROM grade WHERE idCourse = :idCourse")
    LiveData<List<Grade>> getAllByCourse(Integer idCourse);

    @Query("SELECT * FROM grade WHERE id = :id")
    Grade getById(Integer id);

    @Delete
    void delete(Grade grade);

    @Update
    void update(Grade grade);
}
