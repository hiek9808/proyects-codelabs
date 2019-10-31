package com.example.mipromedio.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * Entity Class que representa una sub nota de una nota en la base de datos
 */
@Entity
@Data
public class SubGrade {

    /**
     * Este atributo tiene una anotacion @PrimaryKey porque indica que es la clave primaria
     * de SubGrade ademas se autogenera
     */
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Double percent;
    private Double grade;

    /**
     * Este atributo tiene una anotacion @ForeignKey porque indica que Grade tine una relacion
     * con SubGrade de uno a muchos
     */
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
