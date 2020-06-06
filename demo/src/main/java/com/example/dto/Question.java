package com.example.dto;

import com.example.service.QuestionService;

public class Question {
    private String leftNum;
    private  String rightNum;
    private String comparator;
    private String result;

    public Question(String leftNum, String rightNum, String comparator, String result) {
        this.leftNum = leftNum;
        this.rightNum = rightNum;
        this.comparator = comparator;
        this.result = result;
    }

    public Question() {
    }

    @Override
    public String toString() {
        return leftNum+comparator+rightNum+"="+result;
    }

    public String getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(String leftNum) {
        this.leftNum = leftNum;
    }

    public String getRightNum() {
        return rightNum;
    }

    public void setRightNum(String rightNum) {
        this.rightNum = rightNum;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
