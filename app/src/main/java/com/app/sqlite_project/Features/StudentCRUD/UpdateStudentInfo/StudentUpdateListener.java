package com.app.sqlite_project.Features.StudentCRUD.UpdateStudentInfo;

import com.app.sqlite_project.Features.StudentCRUD.CreateStudent.Student;

public interface StudentUpdateListener {
    void onStudentInfoUpdated(Student student, int position);
}
