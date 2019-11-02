package com.example.mipromedio.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mipromedio.data.model.SubGrade;

import java.util.List;


@Dao
public interface SubGradeDao {

    @Insert
    void create(SubGrade subGrade);

    @Query("SELECT * FROM subgrade WHERE idGrade = :idGrade")
    List<SubGrade> getAllByGrade(Integer idGrade);


}
