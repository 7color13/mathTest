package com.example.service;

import com.example.dto.Answer;
import com.example.dto.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionService {
    public static List<Answer> answerList=new ArrayList<>();

    public static void main(String[] args) {
        List<Question> questionList= getQuestion(33);
        questionList.forEach(System.out::println);
    }
    public static List<Question> getQuestion(int count) {  //获取问题函数
        List<Question> questionList = new ArrayList<>();
        answerList=new ArrayList<>();
        Random rand = new Random();
        for (int i=0;i<count;i++) {
            Question question = null;
            int firstNum = rand.nextInt(99) + 1;
            int secondNum = rand.nextInt(99) + 1;
            String comparator = randomComparator();
            switch (comparator) {
                case "+":
                   question=addUtil(firstNum,secondNum);
                   if (question==null){
                   i=i-1;}
                    break;
                case "-":
                    question=subUtil(firstNum,secondNum);
                    break;
                case "*":
                    question=multiUtil(firstNum,secondNum);
                    if (question==null){
                        i=i-1;}
                    break;
                case "/":
                    question=divideUtil(firstNum,secondNum);
                    break;
            }
            if (question!=null) {
                questionList.add(question);
            }
        }
        return questionList;
    }

    public static String randomComparator() {  //获取随机运算符
        Random rand = new Random();
        String comparator = "";
        int index = rand.nextInt(4);
        switch (index) {
            case 0:
                comparator = "+";
                break;
            case 1:
                comparator = "-";
                break;
            case 2:
                comparator = "*";
                break;
            case 3:
                comparator = "/";
                break;
        }
        return comparator;
    }
    public static Question addUtil(int firstNum,int secondNum){  //加法控制在两位数以内
      Question question = null;
      int result = firstNum+secondNum;
      if (result<100){
           question = randomQuestionMark(firstNum,secondNum,result,"+");
      }
      return question;
    }
    /*问号位置随机函数*/
    public static Question randomQuestionMark(int firstNum,int secondNum,int result,String comparator){
        Question question = new Question();
        Random random = new Random();
        int index = random.nextInt(3);
        Answer answer = new Answer();
        switch (index){
            case 0:
                answer.setAnswer(result);
                question.setResult("?");
                question.setLeftNum(String.valueOf(firstNum));
                question.setRightNum(String.valueOf(secondNum));
                question.setComparator(comparator);
                break;
            case 1:
                answer.setAnswer(secondNum);
                question.setRightNum("?");
                question.setLeftNum(String.valueOf(firstNum));
                question.setComparator(comparator);
                question.setResult(String.valueOf(result));
                break;
            case 2:
                answer.setAnswer(firstNum);
                question.setLeftNum("?");
                question.setRightNum(String.valueOf(secondNum));
                question.setComparator(comparator);
                question.setResult(String.valueOf(result));
                break;
        }
        answerList.add(answer);
        return question;
    }
    /*控制减法在两位数以内函数*/
    public static Question subUtil(int firstNum,int secondNum){
        Question question = null;
        int result =Math.abs(firstNum-secondNum);
        if (firstNum<secondNum){
            question=randomQuestionMark(secondNum,firstNum,result,"-");
        }
        else{
        question = randomQuestionMark(firstNum,secondNum,result,"-");
        }
        return question;
    }
    /*控制乘法在两位数以内函数*/
    public static Question multiUtil(int firstNum,int secondNum){
        Question question = null;
        int result=firstNum*secondNum;
        if(result<100){
            question=randomQuestionMark(firstNum,secondNum,result,"*");
        }
        return question;
    }
    /*控制除法在两位数以内函数*/
    public static Question divideUtil(int firstNum,int secondNum){
        Question question = null;
        int flag;
        int result;
        if (firstNum<secondNum){
            flag = firstNum;
            firstNum=secondNum;
            secondNum=flag;
        }
        result=firstNum/secondNum;
        int rest = firstNum%secondNum;
        if (rest==0){
            randomQuestionMark(firstNum,secondNum,result,"/");
        }
        else{
            question=new Question();
            Answer answer = new Answer();
            question.setResult("?");
            question.setLeftNum(String.valueOf(firstNum));
            question.setRightNum(String.valueOf(secondNum));
            question.setComparator("/");
            answer.setAnswer(result);
            answerList.add(answer);
        }
        return question;
    }


}
