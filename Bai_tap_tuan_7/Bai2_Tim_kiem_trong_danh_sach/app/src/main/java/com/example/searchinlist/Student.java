package com.example.searchinlist;

public class Student {
    private String fullName;
    private String studentId;

    public Student(String fullName, String studentId) {
        this.fullName = fullName;
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentId() {
        return studentId;
    }
}