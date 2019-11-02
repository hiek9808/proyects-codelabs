package com.example.mipromedio.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mipromedio.adapter.SubGradeAdapter;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.data.model.SubGrade;
import com.example.mipromedio.data.repository.GradeRepository;
import com.example.mipromedio.data.repository.SubGradeRepository;

import java.lang.ref.WeakReference;
import java.util.List;

public class GradeViewModel extends AndroidViewModel {

    private GradeRepository mGradeRepository;
    private SubGradeRepository mSubGradeRepository;

    public GradeViewModel(@NonNull Application application) {
        super(application);
        mGradeRepository = new GradeRepository(application);
        mSubGradeRepository = new SubGradeRepository(application);

    }

    public void insert(Grade grade) {
        mGradeRepository.create(grade);
    }

    public void delete(Grade grade) {
    }

    public void insert(SubGrade grade){

    }

    public LiveData<List<Grade>> getAllGradesByCourse(Integer idCourse) {
        return mGradeRepository.getAll(idCourse);
    }

    public void getAllSubGradesByGrade(Integer idSubGrade, SubGradeAdapter adapter){
        new GetAllByGrade(mSubGradeRepository, adapter).execute(idSubGrade);
    }

    private static class GetAllByGrade extends AsyncTask<Integer, Void, List<SubGrade>> {

        private SubGradeRepository mAsyncTaskRepository;
        private WeakReference<SubGradeAdapter> mAdapter;

        GetAllByGrade(SubGradeRepository repository, SubGradeAdapter adapter){
            mAsyncTaskRepository = repository;
            mAdapter = new WeakReference<>(adapter);
        }

        @Override
        protected List<SubGrade> doInBackground(Integer... integers) {
            return mAsyncTaskRepository.getAllByGrade(integers[0]);
        }

        @Override
        protected void onPostExecute(List<SubGrade> subGrades) {
            super.onPostExecute(subGrades);

            mAdapter.get().setSubGrades(subGrades);
        }
    }
}
