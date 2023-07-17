package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Employee;
import com.system.service.EmployeeService;
import com.system.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author Alex Mercer
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-07-17 09:44:02
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




