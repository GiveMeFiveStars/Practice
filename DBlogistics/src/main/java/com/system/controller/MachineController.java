package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.VO.RoseVO;
import com.system.mapper.MachineMapper;
import com.system.pojo.Company;
import com.system.pojo.Employee;
import com.system.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("machine")
public class MachineController {
    @Autowired
    //创建MachineMapper对象，对数据库进行操作！
    MachineMapper machineMapper;

    /**
     * 机电界面转发
     * @return
     */
    @GetMapping("")
    public String machine(){
        return "page/machine";
    }
    /**
     * 机电信息添加界面转发
     * @return
     */
    @GetMapping("/add")
    public String machineAdd(){
        return "page/machine/add";
    }
    @PostMapping("/add/insert")
    @ResponseBody
    //实现“增“操作
    public DataVO<Object> insert(Machine param) {
        Machine machine = machineMapper.selectById(param.getMId());
        if(machine != null){
            return DataVO.fail("添加失败！此机电ID已经存在！");
        }else{
            machineMapper.insert(param);
            return DataVO.success("添加成功");
        }
    }
    /**
     * 实现图表操作
     * @return
     */
    @RequestMapping("/RoseVO")
    @ResponseBody
    public List<RoseVO> getRoseVO(){
        return machineMapper.findRoseVO();
    }

    //改操作！
    /**
     * 员工信息编辑界面转发
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getMachineById(@PathVariable("id")String id,Model model){
        Machine machine = machineMapper.selectById(id);
        model.addAttribute("machine",machine);
        return "page/machine/edit";
    }

    /**
     * 编辑操作更新数据库！
     * @param
     * @return
     */
    @PostMapping  ("")
    @ResponseBody
    public DataVO<Object> updateMachine(Machine machine){
        UpdateWrapper<Machine> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        wrapper.eq("m_id",machine.getMId() );
        //传入不为空的元素更改！
        if (machine.getStashId()!= null) {
            wrapper.set("stash_id", machine.getStashId());
        }
        if (machine.getMStatus()!= null) {
            wrapper.set("m_status",machine.getMStatus());
        }
        machineMapper.update(null, wrapper);
        return DataVO.success("员工信息修改成功!");
    }
    /**
     * 根据主键删除表项
     */
    @DeleteMapping("/delete/{ids}")
    @ResponseBody
    @Transactional
    public DataVO<Object> deleteByIds(@PathVariable("ids") String ids){    //传入主键
        List<Integer> list = new ArrayList<>();
        String res[]=ids.split(",");
        for(int i=0;i< res.length;i++){
            list.add(Integer.parseInt(res[i]));
        }
        int result = this.machineMapper.deleteBatchIds(list);
        if(result > 0){
            return DataVO.success("删除成功");
        }else{
            return DataVO.fail("删除失败,该数据与其他表项关联");
        }
    }
    /**
     * 选择查询所有记录
     * @param page
     * @param limit
     * @param param
     * @return
     */
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
