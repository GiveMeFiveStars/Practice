package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.AdministratorMapper;
import com.system.pojo.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdministratorController {
    @Autowired
    AdministratorMapper administratorMapper;
    @RequestMapping("/Administrator insert")
    public String insert(Integer aId,
                          String aPassword,
                          String aName,
                          Long aPhone,
                          String cName){
        return administratorMapper.insert(new Administrator(aId,
                aPassword,
                aName,
                aPhone,
                cName))>0?"successful":"failed";
    }
    //实现”查“操作！显示表中所有表项！
    @RequestMapping("/Administrator select1")
    public List<Administrator> select1(){
        return administratorMapper.selectList(null); //返回所有
    }
    //根据主键删除表项
    @RequestMapping("/Administrator delete")
    public void delete(Integer aId){    //传入主键
        administratorMapper.deleteById(aId);
    }
    //多条件删除
    @RequestMapping("/Administrator deleteByMap")
    public void deleteByMap( Integer aId,
                             String aPassword,
                             String aName,
                             Long aPhone,
                             String cName) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (aId!= null) {
            map.put("a_id", aId);  //注意！:map的key是数据表的列名
        }
        if (aPassword != null) {
            map.put("a_password", aPassword);
        }
        if ( aName != null) {
            map.put("a_name",  aName);
        }
        if (aPhone != null) {
            map.put("a_phone", aPhone);
        }
        if (cName != null) {
            map.put("c_name", cName);
        }
        //删除满足条件的表项
        administratorMapper.deleteByMap(map);
    }
    //改操作！
    @RequestMapping("/Administrator update")
    public void update(Integer aId,
                       String aPassword,
                       String aName,
                       Long aPhone,
                       String cName) {
        UpdateWrapper<Administrator> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (aId == null) {
            return;
        }
        wrapper.eq("a_id", aId);
        //对传入不为空的元素更改！
        if (aPassword!= null) {
            wrapper.set("a_password", aPassword);
        }
        if (aName!= null) {
            wrapper.set("a_name", aName);
        }
        if (aPhone != null) {
            wrapper.set("a_phone", aPhone);
        }
        if (cName != null) {
            wrapper.set("c_name",cName);
        }
        administratorMapper.update(null, wrapper);
    }
}
