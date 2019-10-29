package com.example.mipromedio.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Double percent;
    private Double grade;

    @ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "idCourse")
    @ColumnInfo(name = "idCourse")
    private Integer idCourse;

    public Grade(String name, Double percent, Double grade, Integer idCourse) {
        this.name = name;
        this.percent = percent;
        this.grade = grade;
        this.idCourse = idCourse;
    }
}
