package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.StashMapper;
import com.system.pojo.Company;
import com.system.pojo.Stash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/stash")
public class StashController {
    @Autowired
    //创建stashMapper对象，对数据库进行操作！
    StashMapper stashMapper;
    /**
     * 仓库界面转发
     * @return
     */
    @GetMapping("")
    public String stash(){
        return "page/stash";
    }
    /**
     * 机电信息添加界面转发
     * @return
     */
    @GetMapping("/add")
    @Transactional
    public String employeeAdd(Model model){
//        List<Company> cIdList = companyMapper.selectList(null);
//        model.addAttribute("cIdList",cIdList);
        return "page/stash/add";
    }
    /**
     * 添加操作更新数据库
     */
    @GetMapping("/add/insert")
    public DataVO<Object> insert(Stash param) {
        stashMapper.insert(param);
        return DataVO.success("添加成功!");
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
        int result = this.stashMapper.deleteBatchIds(list);
        if(result > 0){
            return DataVO.success("删除成功");
        }else{
            return DataVO.fail("删除失败,该数据与其他表项关联");
        }
    }
    /**
     * 选择查询所有记录
     * @param
     * @param page
     * @param limit
     * @return
     */
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
