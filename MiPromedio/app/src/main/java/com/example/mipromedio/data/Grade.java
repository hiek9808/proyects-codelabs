package com.example.mipromedio.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * Entity Class que representa un nota de un curso en la base de datos
 */
@Entity
@Data
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Double percent;
    private Double grade;

    /**
     * Este atributo tiene una anotacion @ForeignKey porque indica que Course tiene un relacion
     * con Grade de uno a muchos
     */
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
