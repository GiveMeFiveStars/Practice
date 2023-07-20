package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.CargoMapper;
import com.system.pojo.Cargo;
import com.system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cargo")
public class CargoController {

    /**
     * 创建PlaceMapper对象，对数据库进行操作！
     */
    @Autowired
    CargoMapper cargoMapper;

    /**
     * 货物管理界面转发
     * @return
     */
    @GetMapping("")
    public String cargo(){
        return "page/cargo";
    }
    /**
     * 货物添加界面转发
     */
    @GetMapping("/add")
    public String cargoAdd(){
        return "page/cargo/add";
    }
    /**
     * 添加操作更新数据库
     * @param param
     * @return
     */
    @RequestMapping   ("/add/insert")
    @ResponseBody
    //实现“增“操作
    public DataVO<Object> insert(Cargo param) {
        Cargo cargo = cargoMapper.selectById(param.getGId());
        if(cargo != null){
            return DataVO.fail("添加失败！此员工ID已经存在！");
        }else{
            cargoMapper.insert(param);
            return DataVO.success("添加成功");
        }
    }
    /**
     * 根据主键删除表项
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/{ids}")
    @ResponseBody
    @Transactional
    public DataVO<Object> deleteByIds(@PathVariable("ids") String ids){    //传入主键
        List<String> list = new ArrayList<>();
        String res[]=ids.split(",");
        for(int i=0;i< res.length;i++){
            list.add(res[i]);
        }
        int result = this.cargoMapper.deleteBatchIds(list);
        if(result > 0){
            return DataVO.success("删除成功");
        }else{
            return DataVO.fail("删除失败,未查到该数据");
        }
    }
    /**
     * 员工信息编辑界面转发
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getCargoById(@PathVariable("id")String id, Model model){
        Cargo cargo = cargoMapper.selectById(id);
        model.addAttribute("cargo",cargo);
        return "page/cargo/edit";
    }

    /**
     * 编辑操作更新数据库！
     * @param cargo
     * @return
     */
    @PostMapping ("/edit/update")
    @ResponseBody
    public DataVO<Object> updateCargo(Cargo cargo){
        UpdateWrapper<Cargo> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        wrapper.eq("g_id", cargo.getGId());
        //传入不为空的元素更改！
        if (cargo.getGType() != null) {
            wrapper.set("g_type", cargo.getGType());
        }
        if (cargo.getSName() != null) {
            wrapper.set("s_name", cargo.getSName());
        }
        if (cargo.getVId()!= null) {
            wrapper.set("v_id", cargo.getVId());
        }
        cargoMapper.update(null, wrapper);
        return DataVO.success("货物信息修改成功!");
    }

    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(int page, int limit,Cargo param) {
        QueryWrapper<Cargo> queryWrapper = new QueryWrapper<>();
        if (param.getGId() != null) {
            queryWrapper.like("g_id", param.getGId());
        }
        if (param.getAdresser() != null) {
            queryWrapper.like("adresser", param.getAdresser());
        }
        if (param.getConsignee()!= null) {
            queryWrapper.like("consignee", param.getConsignee());
        }
        if (param.getWeight() != null) {
            queryWrapper.like("weight", param.getWeight());
        }
        if (param.getDeparturePlace() != null) {
            queryWrapper.like("departure_place", param.getDeparturePlace());
        }
        if (param.getReachPlace() != null) {
            queryWrapper.like("reach_place", param.getReachPlace());
        }
        if (param.getCId() != null) {
            queryWrapper.like("c_id", param.getCId());
        }
        if (param.getGType() != null) {
            queryWrapper.like("g_type", param.getGType());
        }
        if (param.getSName() != null) {
            queryWrapper.like("s_name", param.getSName());
        }
        if (param.getVId() != null) {
            queryWrapper.like("v_id", param.getVId());
        }
        Page<Cargo> pages=new Page<Cargo>(page,limit);
        IPage<Cargo> cargoPage = cargoMapper.selectPage(pages, queryWrapper);
        long count = cargoMapper.selectCount(queryWrapper);
        List<Cargo> list = cargoPage.getRecords();
        return DataVO.success(count, list);
    }
}
