package com.example.mipromedio.data.repository;

import android.app.Application;
import com.example.mipromedio.data.AppDatabase;
import com.example.mipromedio.data.dao.SubGradeDao;
import com.example.mipromedio.data.model.SubGrade;

import java.util.List;

public class SubGradeRepository {

    private SubGradeDao mSubGradeDao;

    public SubGradeRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mSubGradeDao = db.subGradeDao();
    }

    public List<SubGrade> getAllByGrade(Integer idGrade){
        //new GetAllByGrade(mSubGradeDao, adapter).execute(idGrade);
        return mSubGradeDao.getAllByGrade(idGrade);
    }


}
