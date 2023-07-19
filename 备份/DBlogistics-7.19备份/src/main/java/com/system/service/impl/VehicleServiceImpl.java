package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.VO.MapVO;
import com.system.VO.pieVO;
import com.system.pojo.Vehicle;
import com.system.service.VehicleService;
import com.system.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 21628
* @description 针对表【vehicle(车辆信息)】的数据库操作Service实现
* @createDate 2023-07-15 23:31:55
*/
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle>
    implements VehicleService{
    @Autowired
    VehicleMapper vehicleMapper;

    @Override
    public List<pieVO> getpieVO() {
        List<MapVO> list = vehicleMapper.findAllMapVO();
        List<pieVO> pievoList = new ArrayList<>();
        for(MapVO mapVO:list){
            pieVO pievo =new pieVO();
            pievo.setName(mapVO.getStatus().toString());
            pievo.setValue(mapVO.getCount());
            pievoList.add(pievo);
        }
        return pievoList;
    }
}




