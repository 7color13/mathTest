package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Paper {
    @JsonFormat
    private int nid;
    @JsonFormat
    private String question;
    @JsonFormat
    private String answer;

    public Paper(String question, String answer,int nid) {
        this.question = question;
        this.answer = answer;
        this.nid=nid;
    }

    public Paper() {
    }

    @Override
    public String toString() {
        return nid+" "+question+" "+answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }
}
