package com.system.mapper;

import com.system.pojo.Cargo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author Alex Mercer
* @description 针对表【cargo(货物信息)】的数据库操作Mapper
* @createDate 2023-07-19 15:32:53
* @Entity com.system.pojo.Cargo
*/
@Repository
public interface CargoMapper extends BaseMapper<Cargo> {

}




