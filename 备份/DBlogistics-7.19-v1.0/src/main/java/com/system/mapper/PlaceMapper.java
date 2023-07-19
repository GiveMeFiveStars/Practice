package com.system.mapper;

import com.system.pojo.Place;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21628
* @description 针对表【place(地址信息)】的数据库操作Mapper
* @createDate 2023-07-16 09:39:13
* @Entity com.system.pojo.Place
*/
@Repository
public interface PlaceMapper extends BaseMapper<Place> {
}




