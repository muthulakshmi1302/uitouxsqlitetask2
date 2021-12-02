package com.app.sqlite_project.Features.StudentCRUD.CreateStudent;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.sqlite_project.Util.Config;
import com.app.sqlite_project.Database.DatabaseQueryClass;
import com.app.sqlite_project.R;


public class StudentCreateDialogFragment extends DialogFragment {

    private static StudentCreateListener studentCreateListener;

    private EditText nameEditText;
    private EditText mark1EditText;
    private EditText mark2EditText;
    private EditText mark3EditText;
    private EditText rankEditText;
    private EditText resultEditText;
    private Button createButton;
    private Button cancelButton;

    private String nameString = "";
    private long mark1 ;
    private long mark2 ;
    private long mark3 ;
    private long rank;
    private long result;

    public StudentCreateDialogFragment() {
        // Required empty public constructor
    }

    public static StudentCreateDialogFragment newInstance(String title, StudentCreateListener listener){
        studentCreateListener = listener;
        StudentCreateDialogFragment studentCreateDialogFragment = new StudentCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        studentCreateDialogFragment.setArguments(args);

        studentCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return studentCreateDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_create_dialog, container, false);

        nameEditText = view.findViewById(R.id.studentNameEditText);
        mark1EditText = view.findViewById(R.id.mark1EditText);
        mark2EditText = view.findViewById(R.id.mark2EditText);
        mark3EditText = view.findViewById(R.id.mark3EditText);
        rankEditText = view.findViewById(R.id.rankEditText);
        resultEditText = view.findViewById(R.id.resultEditText);
        createButton = view.findViewById(R.id.createButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        String title = getArguments().getString(Config.TITLE);
        getDialog().setTitle(title);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = nameEditText.getText().toString();
                mark1 = Integer.parseInt(mark1EditText.getText().toString());
                mark2 = Integer.parseInt(mark2EditText.getText().toString());
                mark3 = Integer.parseInt(mark3EditText.getText().toString());
                rank = Integer.parseInt(rankEditText.getText().toString());
                result = Integer.parseInt(resultEditText.getText().toString());


                Student student = new Student(-1, nameString, mark1, mark2, mark3,rank,result);

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());

                long id = databaseQueryClass.insertStudent(student);

                if(id>0){
                    student.setId(id);
                    studentCreateListener.onStudentCreated(student);
                    getDialog().dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }

}
