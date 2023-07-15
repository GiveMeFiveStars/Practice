package com.system;

import com.system.mapper.EmployeeMapper;
import com.system.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan("com.system.mapper")
class DemoApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void select1(){
        List<Employee> list= employeeMapper.selectList(null);
        list.forEach(System.out::println);
    }
    public void delete(){    //传入主键
        employeeMapper.deleteById(8888);
    }
    public void deleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("eId", 8885);
    }
}
