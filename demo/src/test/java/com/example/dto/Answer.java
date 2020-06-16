package com.example.dto;

public class Answer {
    private int answer;

    public Answer(int result) {
        this.answer = result;
    }

    public Answer() {
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int result) {
        this.answer = result;
    }

    @Override
    public String toString() {
        return String.valueOf(answer) ;
    }
}
