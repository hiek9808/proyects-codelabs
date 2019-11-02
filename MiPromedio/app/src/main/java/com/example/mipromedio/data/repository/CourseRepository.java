package com.example.mipromedio.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mipromedio.data.AppDatabase;
import com.example.mipromedio.data.dao.CourseDao;
import com.example.mipromedio.data.model.Course;

import java.util.List;

public class CourseRepository {

    private CourseDao mCourseDao;

    public CourseRepository(Application application) {

        AppDatabase db = AppDatabase.getInstance(application);
        mCourseDao = db.courseDao();
    }

    public void create(Course course) {
        new CreateCourseAsyncTask(mCourseDao).execute(course);
    }

    public LiveData<List<Course>> getAll(){
        return mCourseDao.getAllCourses();
    }

    public void delete(Course course) {
        new DeleteCourseAsyncTask(mCourseDao).execute(course);
    }

    private static class CreateCourseAsyncTask extends AsyncTask<Course, Void, Void> {

        private CourseDao mAsyncTaskDao;

        CreateCourseAsyncTask(CourseDao dao){
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Course... courses) {
            mAsyncTaskDao.create(courses[0]);
            return null;
        }
    }


    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void>{

        private CourseDao mAsyncTaskDao;

        public DeleteCourseAsyncTask(CourseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            mAsyncTaskDao.delete(courses[0]);
            return null;
        }
    }
}
