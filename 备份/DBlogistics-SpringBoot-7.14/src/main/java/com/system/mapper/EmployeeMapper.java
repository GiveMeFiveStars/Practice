package com.system.mapper;

import com.system.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author Alex Mercer
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2023-07-18 09:13:36
* @Entity com.system.pojo.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}




