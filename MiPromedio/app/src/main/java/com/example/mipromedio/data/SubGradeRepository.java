package com.example.mipromedio.data;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.mipromedio.adapter.SubGradeAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

public class SubGradeRepository {

    private SubGradeDao mSubGradeDao;

    public SubGradeRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mSubGradeDao = db.subGradeDao();
    }

    public void getAllByGrade(Integer idGrade, SubGradeAdapter adapter){
        new GetAllByGrade(mSubGradeDao, adapter).execute(idGrade);
    }

    private static class GetAllByGrade extends AsyncTask<Integer, Void, List<SubGrade>>{

        private SubGradeDao mAsyncTaskDao;
        private WeakReference<SubGradeAdapter> mAdapter;

        GetAllByGrade(SubGradeDao dao, SubGradeAdapter adapter){
            mAsyncTaskDao = dao;
            mAdapter = new WeakReference<>(adapter);
        }

        @Override
        protected List<SubGrade> doInBackground(Integer... integers) {
            return mAsyncTaskDao.getAllByGrade(integers[0]);
        }

        @Override
        protected void onPostExecute(List<SubGrade> subGrades) {
            super.onPostExecute(subGrades);

            mAdapter.get().setSubGrades(subGrades);
        }
    }
}
