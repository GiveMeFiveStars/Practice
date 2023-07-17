package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.EmployeeMapper;
import com.system.pojo.Employee;
import com.system.service.EmployeeService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    //创建EmployeeMapper对象，对数据库进行操作！
    EmployeeMapper employeeMapper;

    @Autowired
    //创建EmployeeMapper对象，对数据库进行操作！
    EmployeeService employeeService;
    @GetMapping("/insert")
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
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Employee> select1(){
      return employeeMapper.selectList(null);
    }
    //根据主键删除表项
    @GetMapping("/delete")
     public void delete(Integer eId){    //传入主键
         employeeMapper.deleteById(eId);
    }
    //多条件删除
    @GetMapping("/deleteByMap")
    public void deleteByMap(Integer eId,//插入各个属性
                            Integer cId,
                            String  eName,
                            Long  ePhone,
                            String  eSex,
                            BigDecimal salary,
                            String position) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (eId != null) {
            map.put("e_id", eId);  //注意！:map的key是数据表的列名
        }
        if (cId != null) {
            map.put("c_id", cId);
        }
        if (eName != null) {
            map.put("e_name", eName);
        }
        if (ePhone != null) {
            map.put("e_phone", ePhone);
        }
        if (eSex != null) {
            map.put("e_sex", eSex);
        }
        if (salary != null) {
            map.put("salary", salary);
        }
        if (position != null) {
            map.put("position", position);
        }
        //删除满足条件的表项
        employeeMapper.deleteByMap(map);
    }
    //改操作！
    @GetMapping("/update")
    public void update(Integer eId, //插入各个属性
                       Integer cId,
                       String  eName,
                       Long  ePhone,
                       String  eSex,
                       BigDecimal salary,
                       String position) {
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (eId == null) {
            return;
        }
        wrapper.eq("e_id", eId);
        //传入不为空的元素更改！
        if (cId != null) {
            wrapper.set("c_id", cId);
        }
        if (eName != null) {
            wrapper.set("e_name", eName);
        }
        if (ePhone != null) {
            wrapper.set("e_phone", ePhone);
        }
        if (eSex != null) {
            wrapper.set("e_sex", eSex);
        }
        if (salary != null) {
            wrapper.set("salary", salary);
        }
        if (position != null) {
            wrapper.set("position", position);
        }
        employeeMapper.update(null, wrapper);
    }
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public DataVO<Object> listAll(int page,int limit){

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
//        //分页查询Employee信息
//        Page<Employee>pages=new Page<Employee>(page,limit);
//        IPage<Employee> employeePage = employeeMapper.selectPage(pages, queryWrapper);
//        List<Employee> list = employeePage.getRecords();
//        return DataVO.success(employeePage.getTotal(),list);
        List<Employee> list = employeeMapper.selectList(queryWrapper);
        long count = employeeMapper.selectCount(queryWrapper);
        return DataVO.success(count, list);
    }

    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(Integer eId, //插入各个属性
                                 Integer cId,
                                 String  eName,
                                 Long  ePhone,
                                 String  eSex,
                                 BigDecimal salary,
                                 String position) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (eId != null) {
            queryWrapper.like("e_id", eId);
        }
        if (cId != null) {
            queryWrapper.like("c_id", cId);
        }
        if (eName != null) {
            queryWrapper.like("e_name", eName);
        }
        if (ePhone != null) {
            queryWrapper.like("e_phone", ePhone);
        }
        if (eSex != null) {
            queryWrapper.like("e_sex", eSex);
        }
        if (salary != null) {
            queryWrapper.like("salary", salary);
        }
        if (position != null) {
            queryWrapper.like("position", position);
        }
        List<Employee> list = employeeMapper.selectList(queryWrapper);
        long count = employeeMapper.selectCount(queryWrapper);
        return DataVO.success(count, list);
    }
}
