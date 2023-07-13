package com.sichuan.servlet;

import com.sichuan.service.impl.userServiceImpl;
import com.sichuan.service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fsj
 * @date 2023/7/12 21:59
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这里我们为了简单可以在doPost中调用doGet
        doGet(request, response);
        //这里是设置数据库获取到的数据的字符集,不设置可能会导致获取的数据是乱码
        request.setCharacterEncoding("utf-8");
        //从数据库获取用户名和密码
        String username = request.getParameter("username");
        String psssword = request.getParameter("password");
        //这里我们直接调用service层的方法,不必再去调用dao层的方法
        userService user = new userServiceImpl();
        //开始对获取的结果进行判断
        if (user.login(username, psssword)) {
            //登录成功
            //这里我们设置了一个会话来存放我们获取到的用户名,一会我们将会把用户名渲染到页面当中
            request.getSession().setAttribute("username", username);
            //登录成功,采用转发的方式到登录成功的页面,当然也可使用重定向
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            //登录失败,转发到登录失败页面...
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }
}
