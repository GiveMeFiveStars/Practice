package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.VO.RoseVO;
import com.system.mapper.MachineMapper;
import com.system.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("machine")
public class MachineController {
    @Autowired
    //创建MachineMapper对象，对数据库进行操作！
    MachineMapper machineMapper;
    @GetMapping("/insert")
    //实现“增“操作
    public DataVO<Object> insert(Machine param) {
        machineMapper.insert(param);
        return DataVO.success("添加成功!");
    }
    //实现图表操作
    @RequestMapping("/RoseVO")
    @ResponseBody
    public List<RoseVO> getRoseVO(){
        return machineMapper.findRoseVO();
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
                            Integer machine) {
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
        if ( machine != null) {
            map.put("stash_id",  machine);
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
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public DataVO<Object> listAll(int page, int limit){
        QueryWrapper<Machine> queryWrapper = new QueryWrapper<Machine>();
        //分页查询Machine信息
        Page<Machine> pages=new Page<Machine>(page,limit);
        IPage<Machine> machinePage = machineMapper.selectPage(pages, queryWrapper);
        List<Machine> list = machinePage.getRecords();
        return DataVO.success(machinePage.getTotal(),list);
    }

    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(Machine param,int page,int limit) {
        QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
        if (param.getMId() != null) {
            queryWrapper.like("m_id", param.getMId());
        }
        if (param.getMName() != null) {
            queryWrapper.like("m_name", param.getMName());
        }
        if (param.getModelNum() != null) {
            queryWrapper.like("model_num", param.getModelNum());
        }
        if (param.getMStatus() != null) {
            queryWrapper.like("m_status", param.getMStatus());
        }
        if (param.getStashId() != null) {
            queryWrapper.like("stash_id", param.getStashId());
        }
        Page<Machine>pages=new Page<Machine>(page,limit);
        IPage<Machine> machinePage = machineMapper.selectPage(pages, queryWrapper);
        long count = machineMapper.selectCount(queryWrapper);
        List<Machine> list = machinePage.getRecords();
        return DataVO.success(count, list);
    }
}
