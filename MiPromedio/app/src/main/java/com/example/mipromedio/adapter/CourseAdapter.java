package com.example.mipromedio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mipromedio.GradeActivity;
import com.example.mipromedio.R;
import com.example.mipromedio.data.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> mCourses;
    private final LayoutInflater mInflater;
    private Context mContext;

    public CourseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course currentCourse = mCourses.get(position);
            holder.mCourseTextView.setText(currentCourse.getName());
            holder.mGradeCourseTextView.setText(String.valueOf(currentCourse.getId()));
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) return mCourses.size();
        else return 0;
    }

    public void setCourses(List<Course> courses) {
        this.mCourses = courses;
        notifyDataSetChanged();
    }

    public Course getCourseAtPosition(int position){
        return mCourses.get(position);
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView mCourseTextView;
        private final TextView mGradeCourseTextView;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            mCourseTextView = itemView.findViewById(R.id.text_course);
            mGradeCourseTextView = itemView.findViewById(R.id.text_grade_course);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Course currentCourse = mCourses.get(getAdapterPosition());
            Intent intent = new Intent(mContext, GradeActivity.class);
            intent.putExtra("id", currentCourse.getId());
            mContext.startActivity(intent);
        }
    }
}
