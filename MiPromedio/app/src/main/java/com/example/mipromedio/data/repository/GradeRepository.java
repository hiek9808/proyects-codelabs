package com.example.mipromedio.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mipromedio.data.AppDatabase;
import com.example.mipromedio.data.dao.GradeDao;
import com.example.mipromedio.data.model.Grade;

import java.util.List;

public class GradeRepository {

    private GradeDao mGradeDao;

    public GradeRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mGradeDao = db.gradeDao();
    }

    public void create(Grade grade) {
        mGradeDao.create(grade);
    }

    public LiveData<List<Grade>> getAll(Integer id) {
        return mGradeDao.getAllByCourse(id);
    }
}
