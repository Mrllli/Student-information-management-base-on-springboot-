package com.lhl.springboot.springbootdemo.controller;

import java.util.logging.Logger;

import com.lhl.springboot.springbootdemo.Dao.AdminDao;
import com.lhl.springboot.springbootdemo.entity.AdminUser;
import com.lhl.springboot.springbootdemo.service.AdminUserService;
import com.lhl.springboot.springbootdemo.common.Result;
import com.lhl.springboot.springbootdemo.common.ResultCheck;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import javax.annotation.Resource;
/**
 * @author lhl
 */
@Api(tags = "admin")
@RestController
@RequestMapping("/users")
public class AdminController{

    @Resource
    AdminDao adminDao;

    @Autowired
    AdminUserService adminUserService;

    @ApiOperation(value="register")
    @PostMapping(value = "register")
    public boolean Register(String name,String password){
        adminDao.register(name, password);
        return true;
    }

    @ApiOperation(value = "login")
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
            return new Result(token);
        }else{
            System.out.println("登陆失败");
            return new Result("");
        }
    }
    @ApiOperation(value ="check")
    @RequestMapping(value="/check",method=RequestMethod.POST)
    public int check(@RequestBody ResultCheck res){
        AdminUser adminuser = new AdminUser();
        adminuser = adminDao.checkStatus();
        int checkToken = adminuser.getStatus();
        String token = checkToken+"";
        if(res.getToken().contains(token)){
            return 1;

        }else{
            return 2;
        }
    }


}