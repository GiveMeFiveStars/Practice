package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.CargoMapper;
import com.system.pojo.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
     * 添加操作更新数据库
     * @param param
     * @return
     */
    @GetMapping("/add/insert")
    //实现“增“操作
    public DataVO<Object> insert(Cargo param) {
       cargoMapper.insert(param);
        return DataVO.success("添加成功!");
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
