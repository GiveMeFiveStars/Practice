package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Machine;
import com.system.service.MachineService;
import com.system.mapper.MachineMapper;
import org.springframework.stereotype.Service;

/**
* @author 21628
* @description 针对表【machine(机电信息)】的数据库操作Service实现
* @createDate 2023-07-15 22:47:47
*/
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine>
    implements MachineService{

}




