package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.VO.DataVO;
import com.system.VO.pieVO;
import com.system.mapper.EmployeeMapper;
import com.system.mapper.StashMapper;
import com.system.mapper.VehicleMapper;
import com.system.pojo.Company;
import com.system.pojo.Employee;
import com.system.pojo.Stash;
import com.system.pojo.Vehicle;
import com.system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    //创建PlaceMapper对象，对数据库进行操作！
    VehicleMapper vehicleMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StashMapper stashMapper;
    @Autowired
    VehicleService vehicleService;
    /**
     * 车辆管理界面转发
     * @return
     */
    @GetMapping("")
    public String vehicle(){
        return "page/vehicle";
    }
    //实现表操作
    @RequestMapping("/pieVO")
    @ResponseBody
    public List<pieVO> getpieVO(){
        return vehicleService.getpieVO();
    }
    /**
     * 车辆信息编辑界面转发
     * @return
     */
//    @GetMapping("/edit/{id}")
//    public String getCompanyById(@PathVariable("id")String id, Model model){
//        Vehicle vehicle = vehicleMapper.selectById(id);
//        model.addAttribute("Vehicle",vehicle);
//        model.addAttribute("cIdList",vehicleMapper.selectList(null));
//        return "page/vehicle/edit";
//    }
    /**
     * 员工信息添加界面转发
     * @return
     */
    @GetMapping("/add")
    @Transactional
    public String vehicleAdd(Model model){
        List<Employee> employeelist =employeeMapper.selectList(new QueryWrapper<Employee>().eq("position","货车司机"));
        model.addAttribute("employeelist",employeelist);
        List<Stash> stashList =stashMapper.selectList(null);
        model.addAttribute("stashList",stashList);
        return "page/vehicle/add";
    }
    @RequestMapping("/add/insert")
    @ResponseBody
    //实现“增“操作
    public DataVO<Object> insert(Vehicle param) {
        vehicleMapper.insert(param);
        return DataVO.success("添加成功");
    }
    @GetMapping("/edit/{id}")
    public String getvehicleById(@PathVariable("id")String id,Model model){
        List<Employee> employeelist =employeeMapper.selectList(new QueryWrapper<Employee>().eq("position","货车司机"));
        model.addAttribute("employeelist",employeelist);
        List<Stash> stashList =stashMapper.selectList(null);
        model.addAttribute("stashList",stashList);
        Vehicle vehicle = vehicleMapper.selectById(id);
        model.addAttribute("vehicle",vehicle);
        return "page/vehicle/edit";
    }
    /**
     * 编辑操作更新数据库！
     * @param param
     * @return
     */
    @PostMapping  ("/edit/update")
    @ResponseBody
    public DataVO<Object> updateVehicle(Vehicle param){
        UpdateWrapper<Vehicle> wrapper = new UpdateWrapper<>();
        //根据主键进行查询修改，主键不能为空！
        wrapper.eq("v_id", param.getVId());
        //传入不为空的元素更改！
        if (param.getStashId() != null) {
            wrapper.like("stash_id", param.getStashId());
        }
        if (param.getEId() != null) {
            wrapper.like("e_id", param.getEId());
        }
        if (param.getTLimit() != null) {
            wrapper.like("t_limit", param.getTLimit());
        }
        vehicleMapper.update(null, wrapper);
        return DataVO.success("车辆信息修改成功!");
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
        int result = this.vehicleMapper.deleteBatchIds(list);
        if(result > 0){
            return DataVO.success("删除成功");
        }else{
            return DataVO.fail("删除失败,未查到该数据");
        }
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
    public DataVO<Object> select(Vehicle param,int page,int limit) {
        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
        if (param.getVId() != null) {
            queryWrapper.like("v_id", param.getVId());
        }
        if (param.getVType() != null) {
            queryWrapper.like("v_type", param.getVType());
        }
        if (param.getVStatus() != null) {
            queryWrapper.like("v_status", param.getVStatus());
        }
        if (param.getStashId() != null) {
            queryWrapper.like("stash_id", param.getStashId());
        }
        if (param.getEId() != null) {
            queryWrapper.like("e_id", param.getEId());
        }
        if (param.getLicenseId() != null) {
            queryWrapper.like("license_id", param.getLicenseId());
        }
        if (param.getTLimit() != null) {
            queryWrapper.like("t_limit", param.getTLimit());
        }
        Page<Vehicle>pages=new Page<Vehicle>(page,limit);
        IPage<Vehicle> vehiclePage = vehicleMapper.selectPage(pages, queryWrapper);
        long count = vehicleMapper.selectCount(queryWrapper);
        List<Vehicle> list = vehiclePage.getRecords();
        return DataVO.success(count, list);
    }
}
