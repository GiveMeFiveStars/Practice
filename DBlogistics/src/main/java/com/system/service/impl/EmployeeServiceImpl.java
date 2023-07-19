package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.VO.BarVo;
import com.system.VO.EmployeeBarVo;
import com.system.pojo.Employee;
import com.system.service.EmployeeService;
import com.system.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Alex Mercer
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-07-17 09:44:02
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public BarVo getBarVo() {
        List<EmployeeBarVo> list = employeeMapper.findAllEmployeeBarVO();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (EmployeeBarVo employeeBarVo : list) {
            names.add(employeeBarVo.getName());
            values.add(employeeBarVo.getCount());
        }
        BarVo barVo = new BarVo();
        barVo.setNames(names);
        barVo.setValues(values);
        return barVo;
    }
}




