package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Vehicle;
import com.system.service.VehicleService;
import com.system.mapper.VehicleMapper;
import org.springframework.stereotype.Service;

/**
* @author 21628
* @description 针对表【vehicle(车辆信息)】的数据库操作Service实现
* @createDate 2023-07-15 23:31:55
*/
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle>
    implements VehicleService{

}




