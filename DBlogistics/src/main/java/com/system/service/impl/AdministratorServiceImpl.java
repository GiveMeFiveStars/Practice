package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Administrator;
import com.system.service.AdministratorService;
import com.system.mapper.AdministratorMapper;
import org.springframework.stereotype.Service;

/**
* @author 21628
* @description 针对表【administrator(管理员信息)】的数据库操作Service实现
* @createDate 2023-07-16 00:13:01
*/
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator>
    implements AdministratorService{

}




