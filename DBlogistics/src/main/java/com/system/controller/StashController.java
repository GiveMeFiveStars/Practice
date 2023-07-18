package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.StashMapper;
import com.system.pojo.Stash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public DataVO<Object> insert(Stash param) {
        stashMapper.insert(param);
        return DataVO.success("添加成功!");
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
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public DataVO<Object>listAll(int page, int limit){
        QueryWrapper<Stash> queryWrapper = new QueryWrapper<Stash>();
        //分页查询Stash信息
        Page<Stash> pages=new Page<Stash>(page,limit);
        IPage<Stash> stashPage = stashMapper.selectPage(pages, queryWrapper);
        List<Stash> list = stashPage.getRecords();
        return DataVO.success(stashPage.getTotal(),list);
    }

    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(Stash param,int page,int limit) {
        QueryWrapper<Stash> queryWrapper = new QueryWrapper<>();
        if (param.getStashId() != null) {
            queryWrapper.like("stash_id", param.getStashId());
        }
        if (param.getSName() != null) {
            queryWrapper.like("s_name", param.getSName());
        }
        if (param.getSArea() != null) {
            queryWrapper.like("s_area", param.getSArea());
        }
        if (param.getSAdress() != null) {
            queryWrapper.like("s_adress", param.getSAdress());
        }
        if (param.getEName() != null) {
            queryWrapper.like("e_name", param.getEName());
        }
        if (param.getSTime() != null) {
            queryWrapper.like("s_time", param.getSTime());
        }
        if (param.getCTime() != null) {
            queryWrapper.like("c_time", param.getCTime());
        }
        Page<Stash>pages=new Page<Stash>(page,limit);
        IPage<Stash> stashPage = stashMapper.selectPage(pages, queryWrapper);
        long count = stashMapper.selectCount(queryWrapper);
        List<Stash> list = stashPage.getRecords();
        return DataVO.success(count, list);
    }
}
