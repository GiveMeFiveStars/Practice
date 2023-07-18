package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
     * 车辆管理界面转发
     * @return
     */
    @GetMapping("/vehicle")
    public String vehicle(){
        return "page/vehicle";
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
     * 仓库界面转发
     * @return
     */
    @GetMapping("/stash")
    public String stash(){
        return "page/stash";
    }

    /**
     * 机电界面转发
     * @return
     */
    @GetMapping("/machine")
    public String machine(){
        return "page/machine";
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
     * 货物管理界面转发
     * @return
     */
    @GetMapping("/cargo")
    public String cargo(){
        return "page/cargo";
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


}
