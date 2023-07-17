package com.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.EmployeeMapper;
import com.system.pojo.Employee;
import com.system.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan("com.system.mapper")
class DemoApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;
//    @Test
//    public void select1(){
//        List<Employee> list= employeeMapper.selectList(null);
//        list.forEach(System.out::println);
//    }
//    public void delete(){    //传入主键
//        employeeMapper.deleteById(8888);
//    }
//    public void deleteByMap(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("eId", 8885);
//    }

    @Test
    public void selectListtest(){
        employeeMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void PageTest(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        //分页查询Employee信息
        Page<Employee> pages=new Page<Employee>(1,30);
        IPage<Employee> employeePage = employeeMapper.selectPage(pages, queryWrapper);
        List<Employee> list = employeePage.getRecords();
        list.forEach(System.out::println);
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
    }


}
