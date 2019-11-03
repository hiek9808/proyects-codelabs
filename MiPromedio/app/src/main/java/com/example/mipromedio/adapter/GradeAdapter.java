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
    private GradeViewModel mGradeViewModel;

    public GradeAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grade, parent, false);
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        Grade current = mGrades.get(position);
        holder.mNameTextView.setText(current.getName());
        holder.mPercentTextView.setText((int)(current.getPercent() * 100) + "%");
        if (current.getGrade() != null){
            holder.mGradeEditText.setText(String.valueOf(current.getGrade()));
        }
        holder.mGradeTextView.setText(String.valueOf(current.getPercent() * current.getGrade()));
        SubGradeAdapter mAdapter = new SubGradeAdapter(mContext);
        holder.mSubGradeRecyclerView.setAdapter(mAdapter);
        holder.mSubGradeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mGradeViewModel.getAllSubGradesByGrade(current.getId(), mAdapter);
    }

    @Override
    public int getItemCount() {
        if (mGrades != null) return mGrades.size();
        else return 0;
    }

    @Override
    public void onViewRecycled(@NonNull GradeViewHolder holder) {
        super.onViewRecycled(holder);

        holder.saveGrade();
    }


    /**
     * Asigna una lista de notas al RecyclerView
     * @param grades Notas de un curso.
     */
    public void setGrades(List<Grade> grades) {
        this.mGrades = grades;
        notifyDataSetChanged();
    }

    public List<Grade> getGrades() {
        return mGrades;
    }

    public void setGradeViewModel(GradeViewModel mGradeViewModel) {
        this.mGradeViewModel = mGradeViewModel;
    }

    public Grade getGradeAtPosition(Integer position){
        return mGrades.get(position);
    }

    /**
     * ViewHolder del adaptador que contiene los views del item
     */
    public class GradeViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private final TextView mNameTextView;
        private final TextView mPercentTextView;
        private final EditText mGradeEditText;
        private final TextView mGradeTextView;
        private final RecyclerView mSubGradeRecyclerView;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.text_grade_name);
            mPercentTextView = itemView.findViewById(R.id.text_grade_percent);
            mGradeEditText = itemView.findViewById(R.id.edit_text_grade);
            mGradeTextView = itemView.findViewById(R.id.text_grade);
            mSubGradeRecyclerView = itemView.findViewById(R.id.recycler_view_sub_grades);
            mSubGradeRecyclerView.setVisibility(View.GONE);
            mNameTextView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (mSubGradeRecyclerView.getVisibility() == View.GONE){
                mSubGradeRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mSubGradeRecyclerView.setVisibility(View.GONE);
            }
            return true;
        }

        void saveGrade(){
            int position = getAdapterPosition();
            if (position != -1){
                Grade current = mGrades.get(position);
                String name = mNameTextView.getText().toString();
                // TODO Change later
                Double percent = mGrades.get(position).getPercent();
                Double grade = Double.parseDouble(mGradeEditText.getText().toString());
                current.setGrade(grade);
                mGradeViewModel.updateGrade(current);
                //mGrades.set(position, current);
                //mGradeViewModel.updateGrade(gradeUpdated);
            }

        }

    }
}
