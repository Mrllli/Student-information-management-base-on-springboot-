package com.lhl.springboot.springbootdemo.service.impl;

import com.lhl.springboot.springbootdemo.service.AdminUserService;
import com.lhl.springboot.springbootdemo.Dao.AdminDao;
import com.lhl.springboot.springbootdemo.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService{

    @Autowired
    AdminDao adminDao;

    @Override
    public String updateToken(String name){
        AdminUser adminUser = new AdminUser();

        int number = randomNumber();
        adminDao.updateLoginToken(name,number);
        adminUser = adminDao.checkStatus();
        String token = name + adminUser.getStatus() + randomString();
        return token;

    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(1000000000);
    }

    public String randomString(){

        String returnStr = new String();
        String str = "abcdefghijklmnopq1234567890rstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] ar = str.toCharArray();
        for(int i = 0;i<20;i++){
            Random random = new Random();
            int num = random.nextInt(str.length());
            returnStr = returnStr + ar[num];
        }
        return returnStr;
    }

}