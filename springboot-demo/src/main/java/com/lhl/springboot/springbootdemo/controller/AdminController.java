package com.lhl.springboot.springbootdemo.controller;

import com.lhl.springboot.springbootdemo.Dao.AdminDao;
import com.lhl.springboot.springbootdemo.entity.AdminUser;
import com.lhl.springboot.springbootdemo.service.AdminUserService;
import com.lhl.springboot.springbootdemo.common.Result;
import com.lhl.springboot.springbootdemo.common.ResultCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class AdminController{

    @Resource
    AdminDao adminDao;

    @Autowired
    AdminUserService adminUserService;

    @ResponseBody
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(@RequestBody AdminUser admin){

        AdminUser adminuser = new AdminUser();
        String name = admin.getName();
        String password = admin.getPassword();

        adminuser = adminDao.checkStatus();
        String nameCheck=adminuser.getName();
        String passwordCheck=adminuser.getPassword();

        if(name.equals(nameCheck) && password.equals(passwordCheck)){
            System.out.println("登录成功");
            String token = adminUserService.updateToken(nameCheck);
            System.out.println(token);
            Result result = new Result(token);
            return result;
        }else{
            System.out.println("登陆失败");
            Result result = new Result("");
            return result;
        }
    }

    @RequestMapping(value="/check",method=RequestMethod.POST)
    public int check(@RequestBody ResultCheck res){
        AdminUser adminuser = new AdminUser();
        adminuser = adminDao.checkStatus();
        int checkToken = adminuser.getStatus();
        String token = checkToken+"";
        if(res.getToken().indexOf(token) != -1){
            return 1;
        }else{
            return 2;
        }

    }


}