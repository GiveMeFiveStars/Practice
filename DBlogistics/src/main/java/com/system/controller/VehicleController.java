package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.CargoMapper;
import com.system.mapper.VehicleMapper;
import com.system.pojo.Cargo;
import com.system.pojo.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Vehicle")
public class VehicleController {
    @Autowired
    //创建PlaceMapper对象，对数据库进行操作！
    VehicleMapper vehicleMapper;
    @GetMapping ("/insert")
    //实现“增“操作
    public String insert(String vId,//插入各个属性
                         String vType,
                         Integer vStatus,
                         Integer stashId,
                         Integer eId,
                         Integer licenseId,
                         Date tLimit) {
        return vehicleMapper.insert(new Vehicle(vId,//插入各个属性
                vType,
                vStatus,
                stashId,
                eId,
                licenseId,
                tLimit))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Vehicle> select1(){return vehicleMapper.selectList(null);}
    //根据主键删除表项
    @GetMapping("/delete")
    public void delete(Integer gId){    //传入主键
        vehicleMapper.deleteById(gId);
    }
    //多条件删除
    @GetMapping("/deleteByMap")
    public void deleteByMap(String vId,//插入各个属性
                            String vType,
                            Integer vStatus,
                            Integer stashId,
                            Integer eId,
                            Integer licenseId,
                            Date tLimit) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (vId != null) {
            map.put("v_Id",vId);  //注意！:map的key是数据表的列名
        }
        if (vType != null) {
            map.put("v_Type", vType);
        }
        if (vStatus != null) {
            map.put("v_Status",vStatus);
        }
        if (stashId != null) {
            map.put("stash_Id", stashId);
        }
        if (eId != null) {
            map.put("e_Id",eId);
        }
        if (licenseId != null) {
            map.put("license_Id", licenseId);
        }
        if (licenseId != null) {
            map.put("license_Id", licenseId);
        }
        if (tLimit != null) {
            map.put("t_Limit",tLimit);
        }
        //满足条件的表项
        vehicleMapper.deleteByMap(map);
    }
    //改操作！
    @GetMapping("/update")
    public void update(String vId,//插入各个属性
                       String vType,
                       Integer vStatus,
                       Integer stashId,
                       Integer eId,
                       Integer licenseId,
                       Date tLimit) {
        UpdateWrapper<Vehicle> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (vId == null) {
            return;
        }
        wrapper.eq("v_Id",  vId);
        //传入不为空的元素更改！
        if (vType!= null) {
            wrapper.set("v_Type", vType);
        }
        if (vStatus != null) {
            wrapper.set("v_Status", vStatus);
        }
        if (stashId != null) {
            wrapper.set("stash_Id", stashId);
        }
        if (eId!= null) {
            wrapper.set("e_Id", eId);
        }
        if (licenseId != null) {
            wrapper.set("license_Id", licenseId);
        }
        if (tLimit != null) {
            wrapper.set("t_Limit", tLimit);
        }

        vehicleMapper.update(null, wrapper);
    }
}
