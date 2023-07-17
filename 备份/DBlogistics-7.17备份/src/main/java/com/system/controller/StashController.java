package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.PlaceMapper;
import com.system.mapper.StashMapper;
import com.system.pojo.Administrator;
import com.system.pojo.Place;
import com.system.pojo.Stash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Stash")
public class StashController {
    @Autowired
    //创建stashMapper对象，对数据库进行操作！
    StashMapper stashMapper;
    @GetMapping("/insert")
    //实现“增“操作
    public String insert(Integer stashId,//插入各个属性
                         String sName,
                         Integer sArea,
                         String sAdress,
                         Integer cId,
                         String eName,
                         Date sTime,
                         Date cTime) {
        return stashMapper.insert(new Stash(stashId,
                sName,
                sArea,
                sAdress,
                cId,
                eName,
                sTime,
                cTime))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Stash> select1(){return stashMapper.selectList(null);}
    //根据主键删除表项
    @GetMapping
            ("/delete")
    public void delete(Integer stashId){stashMapper.deleteById(stashId);}
    @GetMapping("/deleteByMap")
    public void deleteByMap(Integer stashId,//插入各个属性
                            String sName,
                            Integer sArea,
                            String sAdress,
                            Integer cId,
                            String eName,
                            Date sTime,
                            Date cTime) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (stashId!= null) {
            map.put("stash_id", stashId);  //注意！:map的key是数据表的列名
        }
        if (sName != null) {
            map.put("s_name", sName);
        }
        if (sArea != null) {
            map.put("s_area",  sArea);
        }
        if (sAdress != null) {
            map.put("s_adress", sAdress);
        }
        if (cId!= null) {
            map.put("c_id", cId);
        }
        if (eName != null) {
            map.put("e_name", eName);
        }
        if (sTime != null) {
            map.put("s_time", sTime);
        }
        if (cTime!= null) {
            map.put("c_time", cTime);
        }
        //删除满足条件的表项
        stashMapper.deleteByMap(map);
    }
    //改操作！
    @GetMapping("/update")
    public void update(Integer stashId,//插入各个属性
                       String sName,
                       Integer sArea,
                       String sAdress,
                       Integer cId,
                       String eName,
                       Date sTime,
                       Date cTime) {
        UpdateWrapper<Stash> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (stashId == null) {
            return;
        }
        wrapper.eq("stash_id", stashId);
        //对传入不为空的元素更改！
        if (sName!= null) {
            wrapper.set("s_name", sName);
        }
        if (sArea!= null) {
            wrapper.set("s_area", sArea);
        }
        if (sAdress != null) {
            wrapper.set("s_adress", sAdress);
        }
        if (cId != null) {
            wrapper.set("c_id",cId);
        }
        if (eName!= null) {
            wrapper.set("e_name", eName);
        }
        if (sTime != null) {
            wrapper.set("s_time", sTime);
        }
        if (cTime!= null) {
            wrapper.set("c_time",cTime);
        }
       stashMapper.update(null, wrapper);
    }
}
