package com.lhl.springboot.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    @GetMapping("/")
    public String online(HttpSession session){

        return "index.html";
    }
}
