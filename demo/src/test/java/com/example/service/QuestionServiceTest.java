package com.example.service;

import com.example.dto.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest {
    public static void main(String[] args) {
        /*出题测试*/
        List<Question> questionList =  QuestionService.getQuestion(20);
        questionList.forEach(System.out::println);
    }
}