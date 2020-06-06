package com.example.service;

import com.example.dto.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    public static void main(String[] args) throws Exception{
        /*游客登录测试*/
        User user = UserService.getPri();
        System.out.println(user.toString());
        /*用户登录测试*/
        String info1 = UserService.oldLogin("蒜栏聊凤倘松");
        System.out.println(info1);
        String info2 = UserService.oldLogin("哈瓦那的棕熊");
        System.out.println(info2);
        /*出题测试*/
        List<User> userList =  UserService.getList();
        userList.forEach(System.out::println);
    }


}