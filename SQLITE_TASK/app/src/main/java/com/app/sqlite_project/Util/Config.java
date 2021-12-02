package com.app.sqlite_project.Util;

public class Config {

    public static final String DATABASE_NAME = "student-db";

    //column names of student table
    public static final String TABLE_STUDENT = "student";
    public static final String COLUMN_STUDENT_ID = "_id";
    public static final String COLUMN_STUDENT_NAME = "name";
    public static final String COLUMN_STUDENT_MARK1 = "mark1";
    public static final String COLUMN_STUDENT_MARK2 = "mark2";
    public static final String COLUMN_STUDENT_MARK3 = "mark3";
    public static final String COLUMN_STUDENT_RANK = "rank";
    public static final String COLUMN_STUDENT_RESULT = "result";

    //column names of subject table
    public static final String TABLE_SUBJECT = "subject";
    public static final String COLUMN_SUBJECT_ID = "_id";
    public static final String COLUMN_REGISTRATION_NUMBER = "fk_registration_no";
    public static final String COLUMN_SUBJECT_NAME = "name";
    public static final String COLUMN_SUBJECT_CODE = "subject_code";
    public static final String COLUMN_SUBJECT_CREDIT = "credit";
    public static final String STUDENT_SUB_CONSTRAINT = "student_sub_unique";

    //others for general purpose key-value pair data
    public static final String TITLE = "title";
    public static final String CREATE_STUDENT = "create_student";
    public static final String UPDATE_STUDENT = "update_student";
    public static final String CREATE_SUBJECT = "create_subject";
    public static final String UPDATE_SUBJECT = "update_subject";
    public static final String STUDENT_REGISTRATION = "student_registration";
}
