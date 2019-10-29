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
public class SubGrade {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Double percent;
    private Double grade;

    @ForeignKey(entity = Grade.class, parentColumns = "id", childColumns = "idGrade")
    @ColumnInfo(name = "idGrade")
    private Integer idGrade;

    public SubGrade(String name, Double percent, Double grade, Integer idGrade) {
        this.name = name;
        this.percent = percent;
        this.grade = grade;
        this.idGrade = idGrade;
    }
}
