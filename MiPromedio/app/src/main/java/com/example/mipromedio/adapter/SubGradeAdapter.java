package com.example.mipromedio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mipromedio.R;
import com.example.mipromedio.data.SubGrade;

import java.util.List;

public class SubGradeAdapter extends RecyclerView.Adapter<SubGradeAdapter.SubGradeViewHolder> {

    private List<SubGrade> subGrades;
    private LayoutInflater inflater;

    public SubGradeAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SubGradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sub_grade, parent, false);
        return new SubGradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubGradeViewHolder holder, int position) {
        SubGrade subGrade = subGrades.get(position);
        holder.mNameTextView.setText(subGrade.getName());
        holder.mPercentTextView.setText((int)(subGrade.getPercent() * 100) + "%");
    }

    @Override
    public int getItemCount() {
        if (subGrades != null) return subGrades.size();
        else return 0;
    }


    public void setSubGrades(List<SubGrade> subGrades) {
        this.subGrades = subGrades;
        notifyDataSetChanged();
    }

    public class SubGradeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mPercentTextView;
        private final EditText mGradeEditText;


        public SubGradeViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.text_sub_grade_name);
            mPercentTextView = itemView.findViewById(R.id.text_sub_grade_percent);
            mGradeEditText = itemView.findViewById(R.id.edit_text_sub_grade);
        }
    }
}
