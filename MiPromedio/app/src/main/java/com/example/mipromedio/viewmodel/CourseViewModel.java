package com.example.mipromedio.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mipromedio.data.model.Course;
import com.example.mipromedio.data.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository mCourseRepository;
    private LiveData<List<Course>> mAllCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        mCourseRepository = new CourseRepository(application);
        mAllCourses = mCourseRepository.getAll();
    }

    public void insert(Course course) {
        mCourseRepository.create(course);
    }

    public void delete(Course course) {
        mCourseRepository.delete(course);
    }


    public LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }


}
