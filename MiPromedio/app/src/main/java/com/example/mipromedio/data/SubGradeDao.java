package com.example.mipromedio.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface SubGradeDao {

    @Insert
    void create(SubGrade subGrade);

    @Query("SELECT * FROM subgrade WHERE idGrade = :idGrade")
    List<SubGrade> getAllByGrade(Integer idGrade);


}
