package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.system.mapper.MachineMapper;
import com.system.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Machine")
public class MachineController {
    @Autowired
    //创建MachineMapper对象，对数据库进行操作！
    MachineMapper machineMapper;
    @GetMapping("/insert")
    //实现“增“操作
    public String insert(Integer mId,//插入各个属性
                         String mName,
                         String modelNum,
                         Integer mStatus,
                         Integer stashId) {
        return machineMapper.insert(new Machine(mId,
                mName,
                modelNum,
                mStatus,
               stashId))>0?"successful":"failed";  //正则表达式判断是否插入元素成功
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Machine> select1(){
        return machineMapper.selectList(null);
    }
    //根据主键删除表项
    @GetMapping("/delete")
    public void delete(Integer mId){    //传入主键
        machineMapper.deleteById(mId);
    }
    //多条件删除
    @GetMapping("/deleteByMap")
    public void deleteByMap(Integer mId,//插入各个属性
                            String mName,
                            String modelNum,
                            Integer mStatus,
                            Integer stashId) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (mId != null) {
            map.put("m_id",mId);  //注意！:map的key是数据表的列名
        }
        if (mName != null) {
            map.put("m_name", mName);
        }
        if (modelNum != null) {
            map.put("model_num",modelNum);
        }
        if (mStatus != null) {
            map.put("m_status", mStatus);
        }
        if ( stashId != null) {
            map.put("stash_id",  stashId);
        }
          //满足条件的表项
        machineMapper.deleteByMap(map);
    }
    //改操作！
    @GetMapping("/update")
    public void update(Integer mId,//插入各个属性
                       String mName,
                       String modelNum,
                       Integer mStatus,
                       Integer stashId) {
        UpdateWrapper<Machine> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (mId == null) {
            return;
        }
        wrapper.eq("m_id",  mId);
        //传入不为空的元素更改！
        if (mName!= null) {
            wrapper.set("m_name", mName);
        }
        if (modelNum != null) {
            wrapper.set("model_num", modelNum);
        }
        if (mStatus != null) {
            wrapper.set("m_status", mStatus);
        }
        if (stashId != null) {
            wrapper.set("stash_id",stashId);
        }
        machineMapper.update(null, wrapper);
    }
}
