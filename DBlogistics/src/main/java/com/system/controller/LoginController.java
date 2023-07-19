package com.system.controller;

import com.system.VO.DataVO;
import com.system.mapper.AdministratorMapper;
import com.system.pojo.Administrator;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdministratorMapper administratorMapper;

    @PostMapping("/loginIn")
    public DataVO login(Administrator administrator, HttpServletRequest request, @RequestParam("captcha")String captcha){
        //取出username和password字段
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //验证码的判断
        if(!CaptchaUtil.ver(captcha,request)){
            CaptchaUtil.clear(request);
            return DataVO.fail("验证码错误！请重新输入！");
        }
        //从数据库按用户名查找
        Administrator admin = administratorMapper.selectById(username);
        if(admin != null){
            if(admin.getAPassword().equals(password)){
                /**
                 * 登录成功
                 */
                admin.setAPassword(null);
                session.setAttribute("userinfo",admin);
                return DataVO.success();
            }else{
                return DataVO.fail("用户名或密码错误！");
            }
        }else{
            /**
             * 登录失败
             */
            return DataVO.fail("用户名或密码错误！");
        }
    }
    /**
     * 退出功能
     */
    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();//注销
        return "login";
    }
}
