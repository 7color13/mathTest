package com.example.service;

import com.example.config.Path;
import com.example.dto.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class UserService  {
    public static void main(String[] args) throws Exception{
        String info1 = oldLogin("哈瓦那的棕熊");
        System.out.println(info1);
        String info2 = oldLogin("张三李四王五");
        System.out.println(info2);
    }
    public static User getPri() throws Exception{  //随机生成用户名昵称函数
        List<Object> list1 = TxtSwitchArray.getTXTAsArray("src/main/java/com/example/resources/rankList.txt", "com.example.dto.User");
        List<User> userList=getList();
        list1=new ArrayList<>();
        System.out.println("游客登录");
        String id=getRandomId();
        String userName = getRandomChineseName(6);
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getId().equals(id)||userList.get(i).getUserName().equals(userName)){
                id=getRandomId();
                userName = getRandomChineseName(6);
            }
        }
        User user = new User(userName,id);
        return user;
       /* System.out.println(userList.toString());
        for (User user1:userList){
            Object object = (Object) user1;
            list1.add(object);
        }
        System.out.println(list1.toString());*/
        //TxtSwitchArray.arrayToTXT(list1,"src/main/java/com/example/resources/rankList.txt");
   }
   public static List<User> getList(){   //获取排行榜信息函数
       List<Object> list1 = TxtSwitchArray.getTXTAsArray("src/main/java/com/example/resources/rankList.txt", "com.example.dto.User");
       List<User> userList=new ArrayList<>();

       for (Object object:list1){
           User user = (User)object;
           userList.add(user);
       }
       return userList;
   }

   public static String oldLogin(String info){   //判断用户登录输入的信息是否正确函数
        List<User> userList = getList();
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getId().equals(info)||userList.get(i).getUserName().equals(info)){
                return "成功";
            }
        }
           return "用户名或id错误";
   }
    public static String getRandomChineseName(int len) {  //获取随机中文昵称函数
        String randomName = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            randomName += str;
        }
        return randomName;
    }
    public static String getRandomId(){   //获取随机ID函数
        UUID uuid = UUID.randomUUID();
        String uuidStr=uuid.toString();
        uuidStr = uuidStr.replace("-","");
        return uuidStr;
    }
    /*将信息排序写入文件函数*/
    public static List<User> getRank(String duringTime,double grade,String userName,String id) throws Exception{
        List<User> userList =new ArrayList<>();
        userList=getList();
        User user = new User("0",userName,id,grade,duringTime);
        int index=userList.size();
        int flag=0;
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getUserName().equals(userName)&& userList.get(i).getGrade()>=grade){
                return userList;
            }
            if (userList.get(i).getUserName().equals(userName)&&userList.get(i).getGrade()<grade){
                userList.get(i).setGrade(grade);
                flag=1;
            }
        }
        if (flag!=1){userList.add(user);}
        File file = new File(Path.pathName+"rankList.txt");
        FileWriter fw =new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        String str="rank\tuserName\tid\tgrade\ttestTime\n";
        String rank="0";
        userList = userList.stream().sorted(Comparator.comparing(User::getGrade).reversed()).collect(Collectors.toList());
            System.out.println(userList);
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserName().equals(userName)) {
                    rank = String.valueOf(i + 1);
                    userList.get(i).setRank(rank);
                }
                if (userList.get(i).getGrade() < grade) {
                    userList.get(i).setRank(String.valueOf(Integer.parseInt(userList.get(i).getRank()) + 1));
                }
                str += userList.get(i).toString();
            }
            bw.write(str);

            bw.close();

        return userList;
    }
    /*测试函数*/

}
