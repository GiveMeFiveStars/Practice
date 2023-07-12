package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class LoginController {

    /**
     * 登录验证
     */
    @RequestMapping("/loginIn")
    public void loginIn(HttpServletRequest request) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //将内容打印到Servlet页面
        System.out.print("username:"+username+"<br>");
        System.out.print("password:"+password+"<br>");

    }
}
