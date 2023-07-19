package com.system.mapper;

import com.system.VO.RoseVO;
import com.system.pojo.Machine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 21628
* @description 针对表【machine(机电信息)】的数据库操作Mapper
* @createDate 2023-07-15 22:47:47
* @Entity com.system.pojo.Machine
*/
@Repository
public interface MachineMapper extends BaseMapper<Machine> {
    @Select("select m_name as name,count(m_id) as count from machine group by m_name")
    List<RoseVO> findRoseVO();
}




