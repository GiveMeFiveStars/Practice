package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.CargoMapper;
import com.system.mapper.PlaceMapper;
import com.system.pojo.Cargo;
import com.system.pojo.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CargoController {
    @Autowired
    //创建PlaceMapper对象，对数据库进行操作！
    CargoMapper cargoMapper;
    @RequestMapping("/Cargo insert")
    //实现“增“操作
    public String insert(Long gId,//插入各个属性
                         String adresser,
                         String consignee,
                         Integer weight,
                         String departurePlace,
                         String reachPlace,
                         Integer cId,
                         Integer gType,
                         String sName,
                         String vId) {
        return cargoMapper.insert(new Cargo(gId,
                adresser,
                consignee,
                weight,
                departurePlace,
                reachPlace,
                cId,
                gType,
                sName,
                vId))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！显示表中所有表项！
    @RequestMapping("/Cargo select1")
    public List<Cargo> select1(){return cargoMapper.selectList(null);}
    //根据主键删除表项
    @RequestMapping("/Cargo delete")
    public void delete(Integer gId){    //传入主键
        cargoMapper.deleteById(gId);
    }
    //多条件删除
    @RequestMapping("/Cargo deleteByMap")
    public void deleteByMap(Long gId,//插入各个属性
                            String adresser,
                            String consignee,
                            Integer weight,
                            String departurePlace,
                            String reachPlace,
                            Integer cId,
                            Integer gType,
                            String sName,
                            String vId) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (gId != null) {
            map.put("g_Id",gId);  //注意！:map的key是数据表的列名
        }
        if (adresser != null) {
            map.put("adresser", adresser);
        }
        if (consignee != null) {
            map.put("consignee",consignee);
        }
        if (weight != null) {
            map.put("weight", weight);
        }
        if (departurePlace != null) {
            map.put("departure_Place",departurePlace);
        }
        if (reachPlace != null) {
            map.put("reach_Place", reachPlace);
        }
        if (cId != null) {
            map.put("c_Id", cId);
        }
        if (gType != null) {
            map.put("g_Type",gType);
        }
        if (sName != null) {
            map.put("s_Name", sName);
        }
        if (vId != null) {
            map.put("v_Id", vId);
        }
        //满足条件的表项
        cargoMapper.deleteByMap(map);
    }
    //改操作！
    @RequestMapping("/Cargo update")
    public void update(Long gId,//插入各个属性
                       String adresser,
                       String consignee,
                       Integer weight,
                       String departurePlace,
                       String reachPlace,
                       Integer cId,
                       Integer gType,
                       String sName,
                       String vId) {
        UpdateWrapper<Cargo> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (gId == null) {
            return;
        }
        wrapper.eq("g_id",  gId);
        //传入不为空的元素更改！
        if (adresser!= null) {
            wrapper.set("adresser", adresser);
        }
        if (consignee != null) {
            wrapper.set("consignee", consignee);
        }
        if (weight != null) {
            wrapper.set("weight", weight);
        }
        if (departurePlace!= null) {
            wrapper.set("departure_Place", departurePlace);
        }
        if (reachPlace != null) {
            wrapper.set("reach_Place", reachPlace);
        }
        if (cId != null) {
            wrapper.set("c_Id", cId);
        }
        if (gType!= null) {
            wrapper.set("g_Type", gType);
        }
        if (sName != null) {
            wrapper.set("s_Name", sName);
        }
        if (vId != null) {
            wrapper.set("v_Id", vId);
        }
        cargoMapper.update(null, wrapper);
    }
}
