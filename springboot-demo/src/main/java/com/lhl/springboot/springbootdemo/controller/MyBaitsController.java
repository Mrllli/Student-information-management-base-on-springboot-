package com.lhl.springboot.springbootdemo.controller;

import com.lhl.springboot.springbootdemo.Dao.UserDao;
import com.lhl.springboot.springbootdemo.entity.User;
import com.lhl.springboot.springbootdemo.listener.MyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 */
@Api(tags = "function")
@RestController
public class MyBaitsController {

    @Resource
    UserDao userDao;

    @ApiOperation(value = "人数")
    @GetMapping("/count")
    public String onlineNum(HttpSession session){

        return "有这么多人在线：" + MyListener.count + "人";
    }

    @ApiOperation(value = "展示所有人")
    @PostMapping("/users/query")
    public List<User> queryAll(@RequestBody User user){
        System.out.println(user.getName());
        if (user.getName()=="" || user.getName()==null) {
            return userDao.findAllUsers();
        }
        else {
            return userDao.findStudent(user);
        }
    }

    @ApiOperation(value = "用ID查找人")
    @PostMapping("/users/queryStudentThroughNumber")
    public List<User> queryStudentThroughNum(String num){
        User user = new User();
        user.setStudentnumber(num);
        return userDao.findStudentThroughStudentNumber(user);
    }

    @ApiOperation(value = "插入")
    @PostMapping("/users/insert")
    public boolean insertUser(@RequestBody User user){
        return userDao.insertUser(user) > 0;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/users/update")
    public boolean updUser(@RequestBody User user){
        return userDao.updUser(user) > 0;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/users/delete")
    public boolean del(@RequestBody User user) {
        return userDao.deleteUser(user.getId()) > 0;
    }


}