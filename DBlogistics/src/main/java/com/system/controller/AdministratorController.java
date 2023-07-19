package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.VO.DataVO;
import com.system.mapper.AdministratorMapper;
import com.system.pojo.Administrator;
import com.system.pojo.Place;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("administrator")
public class AdministratorController {
    @Autowired
    AdministratorMapper administratorMapper;
    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public DataVO<Object> updatePassword(String userId, String oldPwd, String newPwd1, String newPwd2) {
        Administrator admin = administratorMapper.selectById(userId);
        if (admin != null){
            if(admin.getAPassword().equals(oldPwd)){
                if(newPwd1.equals(newPwd2)){
                    UpdateWrapper<Administrator> wrapper = new UpdateWrapper<>();
                    wrapper.eq("a_username",userId);
                    wrapper.set("a_password",newPwd1);
                    administratorMapper.update(null,wrapper);
                    return DataVO.success("修改成功");
                }else{
                    return DataVO.fail("两次输入的新密码不一致！");
                }
            }else{
                return DataVO.fail("旧密码错误,无法修改！");
            }
        }else{
            return DataVO.fail("用户不存在！");
        }
    }
}
