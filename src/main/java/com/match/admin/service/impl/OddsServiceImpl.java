package com.match.admin.service.impl;

import com.match.admin.dao.OddsMapper;
import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import com.match.admin.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/31.
 */
@Service
@Transactional
public class OddsServiceImpl extends AbstractService<Odds> implements OddsService {
    @Resource
    private OddsMapper oddsMapper;
}
