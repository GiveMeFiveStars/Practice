package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.CompanyMapper;
import com.system.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyMapper companyMapper;

    /**
     * 合作公司界面转发
     * @return
     */
    @GetMapping("")
    public String company(){
        return "page/company";
    }

    /**
     * 合作公司信息添加界面转发
     * @return
     */
    @GetMapping("/add")
    public String companyAdd(){
        return "page/companyManagement/add";
    }
    @RequestMapping("/add/insert")
    @ResponseBody
    //实现“增“操作
    public DataVO<Object> insert(Company param) {
        companyMapper.insert(param);
        return DataVO.success("添加成功!");
    }
    /**
     * 选择查询所有记录
     * @param param
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(int page,int limit,Company param) {
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
        Page<Company> pages =new Page<Company>(page,limit);
        IPage<Company> companyPage = companyMapper.selectPage(pages, queryWrapper);
        long count = companyMapper.selectCount(queryWrapper);
        List<Company> list = companyPage.getRecords();
        return DataVO.success(count, list);
    }
}
