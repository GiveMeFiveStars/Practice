package com.system.mapper;

import com.system.pojo.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21628
* @description 针对表【administrator(管理员信息)】的数据库操作Mapper
* @createDate 2023-07-15 20:10:05
* @Entity com.system.pojo.Administrator
*/
@Repository
public interface AdministratorMapper extends BaseMapper<Administrator> {
}




