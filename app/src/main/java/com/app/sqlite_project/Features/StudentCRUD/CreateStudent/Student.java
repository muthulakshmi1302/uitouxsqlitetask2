package com.app.sqlite_project.Features.StudentCRUD.CreateStudent;

public class Student {
    private long id;
    private String name;
    private long mark1;
    private long mark2;
    private long mark3;
    private long rank;
    private long result;

    public Student(int id, String name, long mark1, long mark2,long mark3,long rank,long result) {
        this.id = id;
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.rank = rank;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMark1() {
        return mark1;
    }

    public void setMark1(long mark1) {
        this.mark1 = mark1;
    }

    public long getMark2() {
        return mark2;
    }

    public void setMark2(long mark2) {
        this.mark2 = mark2;
    }

    public long getMark3() {
        return mark3;
    }

    public void setMark3(long mark3) {
        this.mark3 = mark3;
    }
    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }
}
