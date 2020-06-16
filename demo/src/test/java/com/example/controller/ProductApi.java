package com.example.controller;

import com.example.dto.*;
import com.example.service.QuestionService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductApi  {
    @GetMapping("/PrimarySchoolMathTest")
    public List<Paper> getPaper(int count){
        List<Paper> paperList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        questionList = QuestionService.getQuestion(count);
        answers = QuestionService.answerList;
        for (int i=0;i<questionList.size();i++){
            Paper paper = new Paper();
            paper.setNid(i);
            paper.setQuestion(questionList.get(i).toString());
            paper.setAnswer(answers.get(i).toString());
            paperList.add(paper);
        }
        return paperList;
    }
    @GetMapping("/randomUserInfo")
    public User getPriInfo() throws Exception{
        return UserService.getPri();
    }
   @GetMapping("/login")
    public String getLogin(String loginInfo){
        return UserService.oldLogin(loginInfo);
   }
   @GetMapping("/write")
    public String writeToTxt(String duringTime,double grade,String userName,String id){
       try {
           UserService.getRank(duringTime,grade,userName,id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "已成功写入文件";
   }
   @GetMapping("/rank")
    public List<User> getRankInfo(){
       List<User> userList = null;
       userList= UserService.getList();
       return userList;
   }
}
