package com.lhl.springboot.springbootdemo.controller;

import com.lhl.springboot.springbootdemo.Dao.UserDao;
import com.lhl.springboot.springbootdemo.entity.User;
import com.lhl.springboot.springbootdemo.listener.MyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "所有人")
    @GetMapping("/users/query")
    public List<User> queryAll(){
        return userDao.findAllUsers();
    }

    @ApiOperation(value = "展示所有人")
    @GetMapping("/users/querystudent")
    public List<User> queryStudent(String name){
        User user = new User();
        user.setName(name);
        return userDao.findStudent(user);

    }

    @ApiOperation(value = "用ID查找人")
    @GetMapping("/users/queryStudentThroughNumber")
    public List<User> queryStudentThroughNum(String num){
        User user = new User();
        user.setStudentnumber(num);
        return userDao.findStudentThroughStudentNumber(user);
    }

    @ApiOperation(value = "插入")
    @GetMapping("/users/insert")
    public boolean insertUser(String name , String studentnumber){
        User user = new User();
        user.setName(name);
        user.setStudentnumber(studentnumber);
        return userDao.insertUser(user) > 0;
    }

    @ApiOperation(value = "更新")
    @GetMapping("/users/update")
    public boolean updUser(Integer id,String name,String studentnumber){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setStudentnumber(studentnumber);
        return userDao.updUser(user) > 0;
    }

    @ApiOperation(value = "删除")
    @GetMapping("/users/delete")
    public boolean insert(Integer id) {
        return userDao.deleteUser(id) > 0;
    }


}