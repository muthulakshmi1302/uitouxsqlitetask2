package com.app.sqlite_project.Features.StudentCRUD.UpdateStudentInfo;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.sqlite_project.Database.DatabaseQueryClass;
import com.app.sqlite_project.Features.StudentCRUD.CreateStudent.Student;
import com.app.sqlite_project.R;
import com.app.sqlite_project.Util.Config;


public class StudentUpdateDialogFragment extends DialogFragment {

    private static long studentRegNo;
    private static int studentItemPosition;
    private static StudentUpdateListener studentUpdateListener;

    private Student mStudent;

    private EditText nameEditText;
    private EditText mark1EditText;
    private EditText mark2EditText;
    private EditText mark3EditText;
    private EditText rankEditText;
    private EditText resultEditText;
    private Button updateButton;
    private Button cancelButton;

    private String nameString = "";
    private long mark1 = -1;
    private long mark2 = -1;
    private long mark3 = -1;
    private long rank = -1;
    private long result = -1;


    private DatabaseQueryClass databaseQueryClass;

    public StudentUpdateDialogFragment() {
        // Required empty public constructor
    }

    public static StudentUpdateDialogFragment newInstance(long registrationNumber, int position, StudentUpdateListener listener){
        studentRegNo = registrationNumber;
        studentItemPosition = position;
        studentUpdateListener = listener;
        StudentUpdateDialogFragment studentUpdateDialogFragment = new StudentUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Update student information");
        studentUpdateDialogFragment.setArguments(args);

        studentUpdateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return studentUpdateDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_update_dialog, container, false);

        databaseQueryClass = new DatabaseQueryClass(getContext());

        nameEditText = view.findViewById(R.id.studentNameEditText);
        mark1EditText = view.findViewById(R.id.mark11TextView);
        mark2EditText = view.findViewById(R.id.mark22TextView);
        mark3EditText = view.findViewById(R.id.mark33TextView);
        rankEditText = view.findViewById(R.id.rank1TextView);
        resultEditText = view.findViewById(R.id.result1TextView);
        updateButton = view.findViewById(R.id.updateStudentInfoButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        String title = getArguments().getString(Config.TITLE);
        getDialog().setTitle(title);

        mStudent = databaseQueryClass.getStudentByRegNum(studentRegNo);

        if(mStudent!=null){
            nameEditText.setText(mStudent.getName());
            mark1EditText.setText(String.valueOf(mStudent.getMark1()));
            mark2EditText.setText(String.valueOf(mStudent.getMark2()));
            mark3EditText.setText(String.valueOf(mStudent.getMark3()));
            rankEditText.setText(String.valueOf(mStudent.getRank()));
            resultEditText.setText(String.valueOf(mStudent.getResult()));


            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nameString = nameEditText.getText().toString();
                    mark1 = Integer.parseInt(mark1EditText.getText().toString());
                    mark2 = Integer.parseInt(mark2EditText.getText().toString());
                    mark3 = Integer.parseInt(mark3EditText.getText().toString());
                    rank = Integer.parseInt(rankEditText.getText().toString());
                    result = Integer.parseInt(resultEditText.getText().toString());


                    mStudent.setName(nameString);
                    mStudent.setMark1(mark1);
                    mStudent.setMark2(mark2);
                    mStudent.setMark3(mark3);
                    mStudent.setRank(rank);
                    mStudent.setResult(result);

                    long id = databaseQueryClass.updateStudentInfo(mStudent);

                    if(id>0){
                        studentUpdateListener.onStudentInfoUpdated(mStudent, studentItemPosition);
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

        }

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
