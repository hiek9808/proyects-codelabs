package com.example.mipromedio.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * Entity Class que representa un curso en la base de datos
 */
@Entity
@Data
public class Course {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public String name;

    public Course(String name) {
        this.name = name;
    }
}
