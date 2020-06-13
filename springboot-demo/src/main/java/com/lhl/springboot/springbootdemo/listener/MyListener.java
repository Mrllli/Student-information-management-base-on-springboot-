package com.lhl.springboot.springbootdemo.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements HttpSessionListener{
    public static int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se){
        System.out.println("新建session");
        count++;
        se.getSession().getServletContext().setAttribute("onLineCount",count);
        System.out.println(count);

    }


    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        System.out.println("消除session");
        count--;
        se.getSession().getServletContext().setAttribute("onLineCount",count);
        System.out.println(count);
    }
}
