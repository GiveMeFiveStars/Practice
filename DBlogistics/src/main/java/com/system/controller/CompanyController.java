package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.CompanyMapper;
import com.system.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {
    @Autowired
    CompanyMapper companyMapper;
    @RequestMapping("/Company insert")
    //实现插入操作表项的操作！
    public String insert( Integer cId,
                          String cName,
                          String representativeName,
                          String cAddress,
                          Integer registeredCapital,
                          Long cPhone){
        return companyMapper.insert(new Company(cId,
                         cName,
                         representativeName,
                         cAddress,
                         registeredCapital,
                         cPhone))>0?"successful":"failed";
    }
    //实现”查“操作！显示表中所有表项！
    @RequestMapping("/Company select1")
    public List<Company> select1(){
        return companyMapper.selectList(null); //返回所有
    }
    //根据主键删除表项
    @RequestMapping("/Company delete")
    public void delete(Integer cId){    //传入主键
        companyMapper.deleteById(cId);
    }
    //多条件删除
    @RequestMapping("/Company deleteByMap")
    public void deleteByMap( Integer cId,
                             String cName,
                             String representativeName,
                             String cAddress,
                             Integer registeredCapital,
                             Long cPhone) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (cId != null) {
            map.put("c_id", cId);  //注意！:map的key是数据表的列名
        }
        if (cName != null) {
            map.put("c_name", cId);
        }
        if (representativeName != null) {
            map.put("representative_name", representativeName);
        }
        if (cAddress != null) {
            map.put("c_address", cAddress);
        }
        if (registeredCapital != null) {
            map.put("registeredCapital", registeredCapital);
        }
        if (registeredCapital != null) {
            map.put("registered_capital", registeredCapital);
        }
        if (cPhone != null) {
            map.put("c_phone", cPhone);
        }
        //删除满足条件的表项
        companyMapper.deleteByMap(map);
    }
    //改操作！
    @RequestMapping("/Company update")
    public void update(Integer cId,
                       String cName,
                       String representativeName,
                       String cAddress,
                       Integer registeredCapital,
                       Long cPhone) {
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (cId == null) {
            return;
        }
        wrapper.eq("c_id", cId);
        //对传入不为空的元素更改！
        if (cName != null) {
            wrapper.set("c_name", cName);
        }
        if (representativeName != null) {
            wrapper.set("representative_name", representativeName);
        }
        if (cAddress != null) {
            wrapper.set("c_address", cAddress);
        }
        if (registeredCapital != null) {
            wrapper.set("registered_capital", registeredCapital);
        }
        if (cPhone != null) {
            wrapper.set("c_phone", cPhone);
        }
        companyMapper.update(null, wrapper);
    }
}
