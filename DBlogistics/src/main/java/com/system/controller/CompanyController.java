package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.CompanyMapper;
import com.system.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Company")
public class CompanyController {
    @Autowired
    CompanyMapper companyMapper;
    @GetMapping("/insert")
    //实现“增“操作
    public DataVO<Object> insert(Company param) {
        companyMapper.insert(param);
        return DataVO.success("添加成功!");
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Company> select1(){
        return companyMapper.selectList(null); //返回所有
    }
    //根据主键删除表项
    @GetMapping("/delete")
    public void delete(Integer cId){    //传入主键
        companyMapper.deleteById(cId);
    }
    //多条件删除
    @GetMapping("/deleteByMap")
    public void deleteByMap( Integer cId,
                             String cName,
                             String representativeName,
                             String cAddress,
                             Integer registeredCapital,
                             Long cPhone) {
        Map<String, Object> map = new HashMap<>();
        //依次判断各属性值是否为空值
        if (cId != null) {
            map.put("c_id", cId);  //注意！:map的key是数据表的列名
        }
        if (cName != null) {
            map.put("c_name", cId);
        }
        if (representativeName != null) {
            map.put("representative_name", representativeName);
        }
        if (cAddress != null) {
            map.put("c_address", cAddress);
        }
        if (registeredCapital != null) {
            map.put("registeredCapital", registeredCapital);
        }
        if (registeredCapital != null) {
            map.put("registered_capital", registeredCapital);
        }
        if (cPhone != null) {
            map.put("c_phone", cPhone);
        }
        //删除满足条件的表项
        companyMapper.deleteByMap(map);
    }

    //改操作！
    @GetMapping("/update")
    public void update(Integer cId,
                       String cName,
                       String representativeName,
                       String cAddress,
                       Integer registeredCapital,
                       Long cPhone) {
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (cId == null) {
            return;
        }
        wrapper.eq("c_id", cId);
        //对传入不为空的元素更改！
        if (cName != null) {
            wrapper.set("c_name", cName);
        }
        if (representativeName != null) {
            wrapper.set("representative_name", representativeName);
        }
        if (cAddress != null) {
            wrapper.set("c_address", cAddress);
        }
        if (registeredCapital != null) {
            wrapper.set("registered_capital", registeredCapital);
        }
        if (cPhone != null) {
            wrapper.set("c_phone", cPhone);
        }
        companyMapper.update(null, wrapper);
    }
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public DataVO<Object> listAll(int page, int limit){
        QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>();
        //分页查询Company信息
        Page<Company> pages=new Page<Company>(page,limit);
        IPage<Company> companyPage = companyMapper.selectPage(pages, queryWrapper);
        List<Company> list = companyPage.getRecords();
        return DataVO.success(companyPage.getTotal(),list);
    }
    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(Company param,int page,int limit) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        if (param.getCId() != null) {
            queryWrapper.like("c_id", param.getCId());
        }
        if (param.getCName() != null) {
            queryWrapper.like("c_name", param.getCName());
        }
        if (param.getRepresentativeName()!= null) {
            queryWrapper.like("representative_name", param.getRepresentativeName());
        }
        if (param.getCAddress() != null) {
            queryWrapper.like("c_address", param.getCAddress());
        }
        if (param.getRegisteredCapital() != null) {
            queryWrapper.like("registered_capital", param.getRegisteredCapital());
        }
        if (param.getCPhone() != null) {
            queryWrapper.like("c_phone", param.getCPhone());
        }
        Page<Company>pages=new Page<Company>(page,limit);
        IPage<Company> companyPage = companyMapper.selectPage(pages, queryWrapper);
        long count = companyMapper.selectCount(queryWrapper);
        List<Company> list = companyPage.getRecords();
        return DataVO.success(count, list);
    }
}
