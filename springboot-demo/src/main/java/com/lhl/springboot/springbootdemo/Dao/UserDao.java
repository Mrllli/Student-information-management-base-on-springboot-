package com.lhl.springboot.springbootdemo.Dao;

import com.lhl.springboot.springbootdemo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 返回数据列表
     *
     * @return
     */
    List<User> findAllUsers();

    /**
     * 查询
     * @param User
     * @return
     */
    User findStudent(User User);

    /**
     * 查询
     * @param User
     * @return
     */
    User findStudentThroughStudentNumber(User User);

    /**
     * 删除
     *
     * @param User
     *@return
     */
    int insertUser(User User);



    /**
     * 删除
     *
     * @param User
     *@return
     */
    int updUser(User User);



    /**
     * 删除
     *
     * @param id
     *@return
     */
    int deleteUser(Integer id);
}