package com.system.controller;

import com.system.VO.DataVO;
import com.system.mapper.AdministratorMapper;
import com.system.pojo.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdministratorMapper administratorMapper;

    /**
     *
     * 登录验证
     * @param request
     * @return
     */
    @PostMapping ("/loginIn")
    public DataVO<Object> loginIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //用户名和密码是否正确
        Administrator admin = administratorMapper.selectById(username);
        if(admin == null){//该用户不存在
            return DataVO.fail("用户名或密码错误");
        }else if(admin.getAPassword() != password) {
            return DataVO.fail("用户名或密码错误");
        }else
            return DataVO.success();
    }
    /**
     * 退出功能
     */
    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();//注销
        return "/login";
    }

}
