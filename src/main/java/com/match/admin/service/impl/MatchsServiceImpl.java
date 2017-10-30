package com.match.admin.service.impl;

import com.match.admin.dao.MatchsMapper;
import com.match.admin.model.Matchs;
import com.match.admin.service.MatchsService;
import com.match.admin.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/30.
 */
@Service
@Transactional
public class MatchsServiceImpl extends AbstractService<Matchs> implements MatchsService {
    @Resource
    private MatchsMapper matchsMapper;

}
