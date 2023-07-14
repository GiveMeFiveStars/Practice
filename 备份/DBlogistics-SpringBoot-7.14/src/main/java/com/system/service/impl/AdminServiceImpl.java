package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Admin;
import com.system.service.AdminService;
import com.system.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
* @author Alex Mercer
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-07-13 23:25:14
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageInfo<Admin> queryAdminAll(Admin admin, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum,limit);
        List<Admin> adminList = adminMapper.queryAdminInfoAll(admin);
        return new PageInfo<>(adminList) ;
    }

    @Override
    public void addAdminSubmit(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public Admin queryAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdminSubmit(Admin admin) {
        adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public void deleteAdminByIds(List<String> ids) {
        for (String id : ids){
            adminMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public Admin queryUserByNameAndPassword(String username, String password) {
        return adminMapper.queryUserByNameAndPassword(username,password);
    }

}




