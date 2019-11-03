package com.example.mipromedio.data.repository;

import android.app.Application;
import android.os.AsyncTask;

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

        new CreateGradeAsyncTask(mGradeDao).execute(grade);
    }

    public LiveData<List<Grade>> getAll(Integer id) {
        return mGradeDao.getAllByCourse(id);
    }

    public void delete(Grade grade) {
        new DeleteGradeAsyncTask(mGradeDao).execute(grade);
    }

    public void update(Grade grade) {
        new UpdateGradeAsyncTask(mGradeDao).execute(grade);
    }

    private static class DeleteGradeAsyncTask extends AsyncTask<Grade, Void, Void> {

        private GradeDao mAsyncTaskDao;

        public DeleteGradeAsyncTask(GradeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Grade... grades) {
            mAsyncTaskDao.delete(grades[0]);
            return null;
        }
    }

    private static class CreateGradeAsyncTask extends AsyncTask<Grade, Void, Void> {

        private GradeDao mAsyncTaskDao;

        CreateGradeAsyncTask(GradeDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Grade... grades) {
            mAsyncTaskDao.create(grades[0]);
            return null;
        }
    }

    private static class UpdateGradeAsyncTask extends AsyncTask<Grade, Void, Void>{

        private GradeDao mAsyncTaskDao;

        public UpdateGradeAsyncTask(GradeDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Grade... grades) {
            mAsyncTaskDao.update(grades[0]);
            return null;
        }
    }
}
