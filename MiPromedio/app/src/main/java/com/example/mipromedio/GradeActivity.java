package com.example.mipromedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mipromedio.adapter.GradeAdapter;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.data.repository.SubGradeRepository;
import com.example.mipromedio.viewmodel.GradeViewModel;

import java.util.List;

public class GradeActivity extends AppCompatActivity {

    SubGradeRepository subGradeRepository;
    private GradeViewModel mGradeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        setTitle(String.valueOf(getIntent().getIntExtra("id", -1)));
        Integer idCourse = getIntent().getIntExtra("id", -1);

        subGradeRepository = new SubGradeRepository(getApplication());
        mGradeViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_grades);
        final GradeAdapter adapter = new GradeAdapter(this);
        adapter.setGradeViewModel(mGradeViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGradeViewModel.getAllGradesByCourse(idCourse).observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                adapter.setGrades(grades);
            }
        });


    }
}
