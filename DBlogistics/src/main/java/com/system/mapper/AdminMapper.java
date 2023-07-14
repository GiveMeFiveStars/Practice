package com.system.mapper;

import com.system.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Alex Mercer
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2023-07-13 23:25:14
* @Entity com.system.pojo.Admin
*/
public interface AdminMapper extends BaseMapper<Admin> {


    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 管理员查询
     */
    List<Admin> queryAdminInfoAll(Admin admin);

    /**
     * 根据用户名和密码查询用户信息
     */
    Admin queryUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}




