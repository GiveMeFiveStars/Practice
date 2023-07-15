package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.pojo.Company;
import com.system.service.CompanyService;
import com.system.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

/**
* @author 21628
* @description 针对表【company(公司信息)】的数据库操作Service实现
* @createDate 2023-07-15 13:35:15
*/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
    implements CompanyService{

}




