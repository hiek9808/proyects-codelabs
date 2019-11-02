package com.example.mipromedio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mipromedio.R;
import com.example.mipromedio.data.model.Grade;
import com.example.mipromedio.viewmodel.GradeViewModel;

import java.util.List;

/**
 * Adapter del RecyclerView que muestra una lista de notas
 */
public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder> {

    private List<Grade> mGrades;
    private Context mContext;
    private final LayoutInflater inflater;
    private SubGradeAdapter mAdapter;
    private GradeViewModel mGradeViewModel;

    public GradeAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grade, parent, false);
        mAdapter = new SubGradeAdapter(mContext);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_sub_grades);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        Grade current = mGrades.get(position);
        holder.mNameTextView.setText(current.getName());
        holder.mPercentTextView.setText((int)(current.getPercent() * 100) + "%");
        if (current.getGrade() != null){
            holder.mGradeTextView.setText(String.valueOf(current.getGrade()));
        }

        mGradeViewModel.getAllSubGradesByGrade(current.getId(), mAdapter);
    }

    @Override
    public int getItemCount() {
        if (mGrades != null) return mGrades.size();
        else return 0;
    }

    /**
     * Asigna una lista de notas al RecyclerView
     * @param grades Notas de un curso.
     */
    public void setGrades(List<Grade> grades) {
        this.mGrades = grades;
        notifyDataSetChanged();
    }

    public void setGradeViewModel(GradeViewModel mGradeViewModel) {
        this.mGradeViewModel = mGradeViewModel;
    }

    /**
     * ViewHolder del adaptador que contiene los views del item
     */
    public class GradeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mPercentTextView;
        private final EditText mGradeEditText;
        private final TextView mGradeTextView;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.text_grade_name);
            mPercentTextView = itemView.findViewById(R.id.text_grade_percent);
            mGradeEditText = itemView.findViewById(R.id.edit_text_grade);
            mGradeTextView = itemView.findViewById(R.id.text_grade);
        }
    }
}
