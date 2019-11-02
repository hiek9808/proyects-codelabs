package com.example.mipromedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mipromedio.adapter.GradeAdapter;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.data.repository.GradeRepository;
import com.example.mipromedio.data.repository.SubGradeRepository;

import java.util.List;

public class GradeActivity extends AppCompatActivity {

    GradeRepository gradeRepository;
    SubGradeRepository subGradeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        setTitle(String.valueOf(getIntent().getIntExtra("id", -1)));
        Integer idCourse = getIntent().getIntExtra("id", -1);

        subGradeRepository = new SubGradeRepository(getApplication());

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_grades);
        final GradeAdapter adapter = new GradeAdapter(this, subGradeRepository);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gradeRepository = new GradeRepository(getApplication());
        gradeRepository.getAll(idCourse).observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                adapter.setGrades(grades);
            }
        });


    }
}
