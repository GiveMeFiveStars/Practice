package com.system.mapper;

import com.system.pojo.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21628
* @description 针对表【company(公司信息)】的数据库操作Mapper
* @createDate 2023-07-15 13:35:14
* @Entity com.system.pojo.Company
*/
@Repository
public interface CompanyMapper extends BaseMapper<Company> {
}




