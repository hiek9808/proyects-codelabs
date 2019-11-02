package com.example.mipromedio;

import android.os.Bundle;

import com.example.mipromedio.adapter.CourseAdapter;
import com.example.mipromedio.data.model.Course;
import com.example.mipromedio.view.AddNewCourseDialogFragment;
import com.example.mipromedio.viewmodel.CourseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddNewCourseDialogFragment.CourseDialogListener{

    private CourseViewModel mCourseViewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCourseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final CourseAdapter adapter = new CourseAdapter(this);


        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createDialog();

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
                        Course course = adapter.getCourseAtPosition(position);

                        mCourseViewModel.delete(course);
                    }
                }
        );
        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_course clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setCourses(){
    }

    public void createDialog(){
        AddNewCourseDialogFragment newFragment = new AddNewCourseDialogFragment();
        newFragment.show(getSupportFragmentManager(), "courses");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment fragment) {
        EditText nNameEditText = fragment.getDialog().findViewById(R.id.name);
        mCourseViewModel.insert(new Course(nNameEditText.getText().toString()));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment fragment) {
        fragment.getDialog().cancel();
    }
}
