package com.system.service;

import com.system.VO.pieVO;
import com.system.pojo.Vehicle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21628
* @description 针对表【vehicle(车辆信息)】的数据库操作Service
* @createDate 2023-07-15 23:31:55
*/
public interface VehicleService extends IService<Vehicle> {
    public List<pieVO> getpieVO();
}
