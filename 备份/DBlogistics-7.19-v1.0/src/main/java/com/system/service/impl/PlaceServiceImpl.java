package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Place;
import com.system.service.PlaceService;
import com.system.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

/**
* @author 21628
* @description 针对表【place(地址信息)】的数据库操作Service实现
* @createDate 2023-07-16 09:39:13
*/
@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place>
    implements PlaceService{

}




