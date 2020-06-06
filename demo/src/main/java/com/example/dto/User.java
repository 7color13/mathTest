package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    @JsonFormat   //使接口转换成Json形式
    private String rank;
    @JsonFormat
    private String userName;
    @JsonFormat
    private String id;
    @JsonFormat
    private double grade;
    @JsonFormat
    private String testTime;

    public User() {
        super();
    }

    @Override
    public String toString() {
        return rank+"\t"+userName+"\t"+id+"\t"+grade+"\t"+testTime+"\n";
    }

    public User(String userName, String id) {
        this.userName = userName;
        this.id = id;
    }

    public User(String rank, String userName, String id, double grade, String testTime) {
        this.rank = rank;
        this.userName = userName;
        this.id = id;
        this.grade = grade;
        this.testTime = testTime;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }
}
