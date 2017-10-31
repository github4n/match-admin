package com.match.admin.service.impl;

import com.match.admin.dao.CompanyMapper;
import com.match.admin.model.Company;
import com.match.admin.service.CompanyService;
import com.match.admin.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/31.
 */
@Service
@Transactional
public class CompanyServiceImpl extends AbstractService<Company> implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;

}
