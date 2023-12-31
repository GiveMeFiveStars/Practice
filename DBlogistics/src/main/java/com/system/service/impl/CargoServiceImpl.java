package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Cargo;
import com.system.service.CargoService;
import com.system.mapper.CargoMapper;
import org.springframework.stereotype.Service;

/**
* @author Alex Mercer
* @description 针对表【cargo(货物信息)】的数据库操作Service实现
* @createDate 2023-07-19 15:32:53
*/
@Service
public class CargoServiceImpl extends ServiceImpl<CargoMapper, Cargo>
    implements CargoService{

}




