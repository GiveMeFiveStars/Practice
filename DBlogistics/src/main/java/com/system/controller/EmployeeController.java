package com.system.controller;

import com.system.mapper.EmployeeMapper;
import com.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class EmployeeController {
    @Autowired
    //创建EmployeeMapper对象，对数据库进行操作！
    EmployeeMapper employeeMapper;
    @RequestMapping("/insert")
    //实现“增“操作
    public String insert(Integer eId,//插入各个属性
                         Integer cId,
                         String  eName,
                         Long  ePhone,
                         String  eSex,
                         BigDecimal salary,
                         String position) {
        return employeeMapper.insert(new Employee(eId,
                cId,
                eName,
                ePhone,
                eSex,
                salary,
                position))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！
   // @RequestMapping("/select1")
   // public List<Employee> select1(){
   //    return EmployeeMapper.selectList(null);
   // }

}
