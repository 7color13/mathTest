package com.example.service;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TxtSwitchArray {
    /*将txt转换成list函数*/
    public static List<Object> getTXTAsArray(String path, String className) {
        List<Object> resultList = new ArrayList<Object>();
        File file = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String title = br.readLine();
            String titles[] = title.split("\\s+");
            String aLine = "";
            Class aClass = Class.forName(className);
            while ((aLine = br.readLine()) != null) {
                String datas[] = aLine.split("\\s+");
                Object obj = aClass.newInstance();
                for (int i = 0; i < titles.length; i++) {
                    String fieldName = titles[i];
                    Field field = aClass.getDeclaredField(fieldName);
                    String getMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = aClass.getMethod(getMethodName, field.getType());
                    method.invoke(obj, convertObject(datas[i], field.getType().getSimpleName()));
                }
                resultList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }
  /*类型转换函数*/
    public static Object convertObject(String data, String type) {  //类型转换函数
        Object obj = null;
        switch (type) {
            case "String":
                obj = new String(data);
                break;
            case "double":
            case "Double":
                obj = new Double(data);
                break;
            case "float":
            case "Float":
                obj = new Float(data);
                break;
            case "int":
            case "Integer":
                obj = new Integer(data);
                break;
            //后面可以扩充转换为其他数据类型
        }
        return obj;
    }
    /*将list写入txt文件函数*/
    public static void arrayToTXT(List<Object> list, String path) {   //list转TXT
        File file = new File(path);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (list != null && list.size() != 0) {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                String str = "";
                String keyValue = "";
                Object obj = list.get(0);
                Field[] fields = obj.getClass().getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    str += fields[j].getName() + "\t";
                }
                str += "\n";
                bw.write(str);
                for (int i = 0; i < list.size(); i++) {

                    Object obj1 = list.get(i);
                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        keyValue += fields[j].get(obj1).toString() + "\t";
                    }
                    keyValue += "\n";
                }
                bw.write(keyValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
