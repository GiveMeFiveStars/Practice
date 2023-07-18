package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.mapper.CompanyMapper;
import com.system.mapper.EmployeeMapper;
import com.system.pojo.Company;
import com.system.pojo.Employee;
import com.system.service.CompanyService;
import com.system.service.EmployeeService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    //创建EmployeeMapper对象，对数据库进行操作！
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    //创建EmployeeService对象，对数据库进行操作！
    CompanyMapper companyMapper;

    /**
     * 员工管理界面转发
     * @return
     */
    @GetMapping("")
    public String employeeManagement(){
        return "page/employeeManagement";
    }
    /**
     * 员工信息添加
     * @return
     */
    @GetMapping("/add")
    public String employeeAdd(Model model){
        List<Company> cIdList = companyMapper.selectList(null);
        model.addAttribute("cIdList",cIdList);
        return "page/employeeManagement/add";
    }

    /**
     * 员工信息编辑界面转发
     * @return
     */
    @GetMapping("/edit")
    public String employeeEdit(){
        return "page/employeeManagement/edit";
    }
    @RequestMapping("/add/insert")
    @ResponseBody
    //实现“增“操作
    public DataVO<Object> insert(Employee param) {

        employeeMapper.insert(param);

        return DataVO.success("添加成功");
    }
    //实现”查“操作！显示表中所有表项！
    @GetMapping("/select1")
    public List<Employee> select1(){
      return employeeMapper.selectList(null);
    }
    /**
     * 根据主键删除表项
     */
    @DeleteMapping("/{ids}")
    @ResponseBody
    public DataVO<Object> deleteByIds(@PathVariable("ids") String ids){    //传入主键
        List<Integer> list = new ArrayList<>();
        String res[]=ids.split(",");
        for(int i=0;i< res.length;i++){
            list.add(Integer.parseInt(res[i]));
        }
        int result = this.employeeMapper.deleteBatchIds(list);
        if(result > 0){
            return DataVO.success("删除成功");
        }else{
            return DataVO.fail("删除失败,未查到该数据");
        }
    }
    //改操作！
    @GetMapping("/update")
    public void update(Integer eId, //插入各个属性
                       Integer cId,
                       String  eName,
                       Long  ePhone,
                       String  eSex,
                       BigDecimal salary,
                       String position) {
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        if (eId == null) {
            return;
        }
        wrapper.eq("e_id", eId);
        //传入不为空的元素更改！
        if (cId != null) {
            wrapper.set("c_id", cId);
        }
        if (eName != null) {
            wrapper.set("e_name", eName);
        }
        if (ePhone != null) {
            wrapper.set("e_phone", ePhone);
        }
        if (eSex != null) {
            wrapper.set("e_sex", eSex);
        }
        if (salary != null) {
            wrapper.set("salary", salary);
        }
        if (position != null) {
            wrapper.set("position", position);
        }
        employeeMapper.update(null, wrapper);
    }
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public DataVO<Object> listAll(int page,int limit){

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        //分页查询Employee信息
        Page<Employee>pages=new Page<Employee>(page,limit);
        IPage<Employee> employeePage = employeeMapper.selectPage(pages, queryWrapper);
        List<Employee> list = employeePage.getRecords();
        return DataVO.success(employeePage.getTotal(),list);
    }

    @GetMapping("/selectBy")
    @ResponseBody
    @Transactional
    public DataVO<Object> select(int page,int limit,Employee param) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (param.getEId() != null) {
            queryWrapper.like("e_id", param.getEId());
        }
        if (param.getCId() != null) {
            queryWrapper.like("c_id", param.getCId());
        }
        if (param.getEName() != null) {
            queryWrapper.like("e_name", param.getEName());
        }
        if (param.getEPhone() != null) {
            queryWrapper.like("e_phone", param.getEPhone());
        }
        if (param.getESex() != null) {
            queryWrapper.like("e_sex", param.getESex());
        }
        if (param.getSalary() != null) {
            queryWrapper.like("salary", param.getSalary());
        }
        if (param.getPosition() != null) {
            queryWrapper.like("position", param.getPosition());
        }
        Page<Employee> pages = new Page<Employee>(page,limit);
        IPage<Employee> employeePage = employeeMapper.selectPage(pages, queryWrapper);
        long count = employeeMapper.selectCount(queryWrapper);
        List<Employee> list = employeePage.getRecords();
        return DataVO.success(count, list);
    }
}
