package com.app.sqlite_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.app.sqlite_project.Features.StudentCRUD.CreateStudent.Student;
import com.app.sqlite_project.Features.SubjectCRUD.CreateSubject.Subject;
import com.app.sqlite_project.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertStudent(Student student){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_MARK1, student.getMark1());
        contentValues.put(Config.COLUMN_STUDENT_MARK2, student.getMark2());
        contentValues.put(Config.COLUMN_STUDENT_MARK3, student.getMark3());
        contentValues.put(Config.COLUMN_STUDENT_RANK, student.getRank());
        contentValues.put(Config.COLUMN_STUDENT_RESULT, student.getResult());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_STUDENT, null, contentValues);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    public List<Student> getAllStudent(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null, null, null, null, null, null, null);

            /**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

                 String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Student> studentList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                        long mark1 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK1));
                        long mark2 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK2));
                        long mark3 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK3));
                        long rank = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_RANK));
                        long result = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_RESULT));


                        studentList.add(new Student(id, name, mark1, mark2, mark3,rank,result));
                    }   while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public Student getStudentByRegNum(long studentrank){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Student student = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null,
                    Config.COLUMN_STUDENT_RANK + " = ? ", new String[]{String.valueOf(studentrank)},
                    null, null, null);

            /**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

                 String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                long mark1 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK1));
                long mark2 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK2));
                long mark3 = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_MARK3));
                long rank = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_RANK));
                long result = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_RESULT));


                student =new Student(id, name, mark1, mark2, mark3,rank,result);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return student;
    }

    public long updateStudentInfo(Student student){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_MARK1, student.getMark1());
        contentValues.put(Config.COLUMN_STUDENT_MARK2, student.getMark2());
        contentValues.put(Config.COLUMN_STUDENT_MARK3, student.getMark3());
        contentValues.put(Config.COLUMN_STUDENT_RANK, student.getRank());
        contentValues.put(Config.COLUMN_STUDENT_RESULT, student.getResult());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_STUDENT, contentValues,
                    Config.COLUMN_STUDENT_ID + " = ? ",
                    new String[] {String.valueOf(student.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public long deleteStudentByRegNum(long registrationNum) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                                    Config.COLUMN_STUDENT_RANK + " = ? ",
                                    new String[]{ String.valueOf(registrationNum)});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public boolean deleteAllStudents(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_STUDENT, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

    public long getNumberOfStudent(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    // subjects
    public long insertSubject(Subject subject, long registrationNo){
        long rowId = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());
        contentValues.put(Config.COLUMN_REGISTRATION_NUMBER, registrationNo);

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_SUBJECT, null, contentValues);
        } catch (SQLiteException e){
            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }

    public Subject getSubjectById(long subjectId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Subject subject = null;

        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SUBJECT, null,
                    Config.COLUMN_SUBJECT_ID + " = ? ", new String[] {String.valueOf(subjectId)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                String subjectName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SUBJECT_NAME));
                int subjectCode = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CODE));
                double subjectCredit = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CREDIT));

                subject = new Subject(subjectId, subjectName, subjectCode, subjectCredit);
            }
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return subject;
    }

    public long updateSubjectInfo(Subject subject){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_SUBJECT, contentValues,
                    Config.COLUMN_SUBJECT_ID + " = ? ",
                    new String[] {String.valueOf(subject.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public List<Subject> getAllSubjectsByRegNo(long registrationNo){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<Subject> subjectList = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SUBJECT,
                    new String[] {Config.COLUMN_SUBJECT_ID, Config.COLUMN_SUBJECT_NAME, Config.COLUMN_SUBJECT_CODE, Config.COLUMN_SUBJECT_CREDIT},
                    Config.COLUMN_REGISTRATION_NUMBER + " = ? ",
                    new String[] {String.valueOf(registrationNo)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                subjectList = new ArrayList<>();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_ID));
                    String subjectName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SUBJECT_NAME));
                    int subjectCode = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CODE));
                    double subjectCredit = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CREDIT));

                    subjectList.add(new Subject(id, subjectName, subjectCode, subjectCredit));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return subjectList;
    }

    public boolean deleteSubjectById(long subjectId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                Config.COLUMN_SUBJECT_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }

    public boolean deleteAllSubjectsByRegNum(long registrationNum) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                Config.COLUMN_REGISTRATION_NUMBER + " = ? ", new String[]{String.valueOf(registrationNum)});

        return row > 0;
    }

    public long getNumberOfSubject(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_SUBJECT);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

}