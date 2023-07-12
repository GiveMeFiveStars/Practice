package com.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fsj
 * @date 2023/7/12 8:21
 */
@WebServlet(name = "MyServlet" , urlPatterns = "/aa")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");//设置页面编码方式，避免生成乱码
        PrintWriter out = response.getWriter();
        String user = request.getParameter("username");//获取username框里的内容
        String password = request.getParameter("password");//获取password框里的内容
        //将内容打印到Servlet页面
        out.print("username:"+user+"<br>");
        out.print("password:"+password+"<br>");

    }
}
