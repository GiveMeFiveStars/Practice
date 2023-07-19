package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.PlaceMapper;

import com.system.pojo.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Place")
public class PlaceController {
    @Autowired
    //创建PlaceMapper对象，对数据库进行操作！
    PlaceMapper placeMapper;
    @GetMapping ("/insert")
    //实现“增“操作
    public String insert(Integer pId,//插入各个属性
                         String pName,
                         String city,
                         String country) {
        return placeMapper.insert(new Place(pId, pName, city, country))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Place> select1(){return placeMapper.selectList(null);}
    //根据主键删除表项
    @GetMapping("/delete")
    public void delete(Integer pId){    //传入主键
        placeMapper.deleteById(pId);
    }
    //多条件删除
    @GetMapping("/deleteByMap")
    public void deleteByMap(Integer pId,//插入各个属性
                            String pName,
                            String city,
                            String country) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (pId != null) {
            map.put("p_id",pId);  //注意！:map的key是数据表的列名
        }
        if (pName != null) {
            map.put("p_name", pName);
        }
        if (city != null) {
            map.put("city",city);
        }
        if (country != null) {
            map.put("country", country);
        }
        //满足条件的表项
        placeMapper.deleteByMap(map);
    }
    //改操作！
    @GetMapping("/update")
    public void update(Integer pId,//插入各个属性
                       String pName,
                       String city,
                       String country) {
        UpdateWrapper<Place> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (pId == null) {
            return;
        }
        wrapper.eq("p_id",  pId);
        //传入不为空的元素更改！
        if (pName!= null) {
            wrapper.set("pName", pName);
        }
        if (city != null) {
            wrapper.set("city", city);
        }
        if (country != null) {
            wrapper.set("country", country);
        }
        placeMapper.update(null, wrapper);
    }
}
