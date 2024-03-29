package com.example.mipromedio.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mipromedio.data.dao.CourseDao;
import com.example.mipromedio.data.dao.GradeDao;
import com.example.mipromedio.data.dao.SubGradeDao;
import com.example.mipromedio.data.model.Course;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.data.model.SubGrade;

@Database(entities = {Course.class, Grade.class, SubGrade.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;
    public abstract CourseDao courseDao();
    public abstract GradeDao gradeDao();
    public abstract SubGradeDao subGradeDao();

    private static AppDatabase.Callback sRoomDataBaseCallBack = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static AppDatabase getInstance(final Context context){
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Average_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDataBaseCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CourseDao mCourseDao;
        private final GradeDao mGradeDao;
        private final SubGradeDao mSubGradeDao;

        PopulateDbAsync(AppDatabase db){
            mCourseDao = db.courseDao();
            mGradeDao = db.gradeDao();
            mSubGradeDao = db.subGradeDao();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (mCourseDao.getAnyCourse().length < 1){
                mCourseDao.create(new Course("Example Course"));
                Log.d("course: ", mCourseDao.getAllCourses().toString());
                mGradeDao.create(new Grade("grade 1", 0.3, 16.0, 1));
                mGradeDao.create(new Grade("grade 2", 0.4, 16.0, 1));
                mGradeDao.create(new Grade("grade 3", 0.1, 16.0, 1));
                mGradeDao.create(new Grade("grade 4", 0.2, 16.0, 1));
                mGradeDao.create(new Grade("grade 5", 0.3, 16.0, 1));
                mGradeDao.create(new Grade("grade 6", 0.4, 16.0, 1));
                mGradeDao.create(new Grade("grade 7", 0.1, 16.0, 1));
                mGradeDao.create(new Grade("grade 8", 0.2, 16.0, 1));
                mGradeDao.create(new Grade("grade 9", 0.3, 16.0, 1));
                mGradeDao.create(new Grade("grade 10", 0.4, 16.0, 1));
                mGradeDao.create(new Grade("grade 11", 0.1, 16.0, 1));
                mGradeDao.create(new Grade("grade 12", 0.2, 16.0, 1));
                Log.d("course: ", mGradeDao.getAllByCourse(1).toString());
                mSubGradeDao.create(new SubGrade("sub grade 1", 0.5, 16.0, 1));
                mSubGradeDao.create(new SubGrade("sub grade 2", 0.5, 16.0, 1));
                Log.d("sub grade: ", mSubGradeDao.getAllByGrade(1).toString() );
                mSubGradeDao.create(new SubGrade("sub grade 3", 0.5, 16.0, 2));
                mSubGradeDao.create(new SubGrade("sub grade 4", 0.5, 16.0, 2));
                mSubGradeDao.create(new SubGrade("sub grade 5", 0.5, 16.0, 3));
                mSubGradeDao.create(new SubGrade("sub grade 6", 0.5, 16.0, 3));

            }


            return null;
        }
    }

}
