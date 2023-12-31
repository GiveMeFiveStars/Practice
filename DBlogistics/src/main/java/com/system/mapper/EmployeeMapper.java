package com.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.system.VO.CircleVO;
import com.system.VO.EmployeeBarVo;
import com.system.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author Alex Mercer
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2023-07-17 09:44:02
* @Entity com.system.pojo.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("select position as name,count(e_id) as count from employee group by position")
    List<EmployeeBarVo> findAllEmployeeBarVO();
    @Select("select e_sex as name,count(e_id) as value from employee group by e_sex")
    List<CircleVO> findAllCircleVO();
}




