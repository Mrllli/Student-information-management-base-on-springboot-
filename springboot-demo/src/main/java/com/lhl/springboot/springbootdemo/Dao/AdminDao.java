package com.lhl.springboot.springbootdemo.Dao;

import com.lhl.springboot.springbootdemo.entity.AdminUser;

import java.util.List;
import java.util.Map;

public interface AdminDao{

    /**
     * 返回数据列表
     *
     * @return
     */

    AdminUser checkStatus();

    /**
     * 返回数据列表
     * @param name
     * @param number
     * @return
     */
    int updateLoginToken(String name,int number);



}

