package com.example.mipromedio.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                Log.d("course: ", mGradeDao.getAllByCourse(1).toString());
                mSubGradeDao.create(new SubGrade("sub grade 1", 0.5, 16.0, 1));
                mSubGradeDao.create(new SubGrade("sub grade 2", 0.5, 16.0, 1));
                Log.d("sub grade: ", mSubGradeDao.getAllByGrade(1).toString() );
            }


            return null;
        }
    }

}
