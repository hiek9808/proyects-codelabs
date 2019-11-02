package com.example.mipromedio.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mipromedio.R;

public class AddNewCourseDialogFragment extends DialogFragment {

    private CourseDialogListener listener;

    public AddNewCourseDialogFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (CourseDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement CourseDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_course, null);
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(AddNewCourseDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(AddNewCourseDialogFragment.this);
                    }
                });
        return builder.create();
    }

    public interface CourseDialogListener{
        public void onDialogPositiveClick(DialogFragment fragment);
        public void onDialogNegativeClick(DialogFragment fragment);

    }
}
