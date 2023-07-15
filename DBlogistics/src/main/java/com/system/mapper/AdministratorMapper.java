package com.system.mapper;

import com.system.pojo.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
* @author Alex Mercer
* @description 针对表【administrator(管理员信息)】的数据库操作Mapper
* @createDate 2023-07-15 16:35:17
* @Entity com.system.pojo.Administrator
*/
@Repository
public interface AdministratorMapper extends BaseMapper<Administrator> {

}




