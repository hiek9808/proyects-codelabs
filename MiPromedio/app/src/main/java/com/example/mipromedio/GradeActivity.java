package com.example.mipromedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mipromedio.adapter.GradeAdapter;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.data.repository.SubGradeRepository;
import com.example.mipromedio.viewmodel.GradeViewModel;

import java.util.List;

public class GradeActivity extends AppCompatActivity {

    SubGradeRepository subGradeRepository;
    private GradeViewModel mGradeViewModel;
    private GradeAdapter mGradeAdapter;
    private Integer mIdCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        mIdCourse = getIntent().getIntExtra("id", -1);
        String nameCourse = getIntent().getStringExtra("name");
        setTitle(nameCourse);


        subGradeRepository = new SubGradeRepository(getApplication());
        mGradeViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_grades);
        mGradeAdapter = new GradeAdapter(this);
        mGradeAdapter.setGradeViewModel(mGradeViewModel);
        recyclerView.setAdapter(mGradeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGradeViewModel.getAllGradesByCourse(mIdCourse).observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                mGradeAdapter.setGrades(grades);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Grade grade = mGradeAdapter.getGradeAtPosition(position);

                        mGradeViewModel.delete(grade);
                    }
                }
        );
        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_grade){
            mGradeViewModel.insertGrade(new Grade("grade #", 0.2, 16.0, mIdCourse));
        }
        return super.onOptionsItemSelected(item);
    }
}
