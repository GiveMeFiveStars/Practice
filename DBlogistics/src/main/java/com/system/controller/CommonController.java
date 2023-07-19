package com.system.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {
    /**
     * 登录页面的转发
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 主页界面转发
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 欢迎界面转发
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "page/welcome";
    }

    /**
     * 统计分析界面转发
     * @return
     */
    @GetMapping("/statistics")
    public String statistics(){
        return "page/statistics";
    }


    /**
     * 公司简介界面转发
     * @return
     */
    @GetMapping("/companyProfile")
    public String companyProfile(){
        return "page/companyProfile";
    }


    /**
     * 用户密码修改界面转发
     * @return
     */
    @GetMapping("/userPassword")
    public String userPassword(){
        return "page/admin/user-password";
    }

    /**
     * 用户基本信息界面转发
     * @return
     */
    @GetMapping("/userSetting")
    public String userSetting(){
        return "page/admin/user-setting";
    }
    /**
     * 验证码功能实现
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws Exception{
        CaptchaUtil.out(request,response);
    }
}
