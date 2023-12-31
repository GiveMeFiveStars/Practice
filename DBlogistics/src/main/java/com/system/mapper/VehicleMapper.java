package com.system.mapper;

import com.system.VO.MapVO;
import com.system.pojo.Vehicle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 21628
* @description 针对表【vehicle(车辆信息)】的数据库操作Mapper
* @createDate 2023-07-15 23:31:55
* @Entity com.system.pojo.Vehicle
*/
@Repository
public interface VehicleMapper extends BaseMapper<Vehicle> {
    @Select("select v_status as status,count(v_id) as count from vehicle group by v_status")
    List<MapVO> findAllMapVO();
}




