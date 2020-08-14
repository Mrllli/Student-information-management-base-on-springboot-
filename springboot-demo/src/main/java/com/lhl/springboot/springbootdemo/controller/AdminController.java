package com.lhl.springboot.springbootdemo.controller;

import com.lhl.springboot.springbootdemo.Dao.AdminDao;
import com.lhl.springboot.springbootdemo.entity.AdminUser;
import com.lhl.springboot.springbootdemo.service.AdminUserService;
import com.lhl.springboot.springbootdemo.common.Result;
import com.lhl.springboot.springbootdemo.common.ResultCheck;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


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
    @PostMapping(value = "/register")
    public boolean register(String name,String password){
        adminDao.register(name, password);
        return true;
    }

    @ApiOperation(value = "login")
    @ResponseBody
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(@RequestBody AdminUser admin){

        AdminUser adminuser;
        String name = admin.getName();
        String password = admin.getPassword();

        adminuser = adminDao.checkStatus(name);
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
    public boolean check(@RequestBody ResultCheck res){
        if (res.getToken()==""||res.getToken()==null) {
            return false;
        }
        System.out.println(res.getToken());
        AdminUser adminuser;
        adminuser = adminDao.checkStatus("admin");
        int checkToken = adminuser.getStatus();
        String token = checkToken+"";
        if(res.getToken().contains(token)){
            return true;

        }else{
            return false;
        }
    }


}