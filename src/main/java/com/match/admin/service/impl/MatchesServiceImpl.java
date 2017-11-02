package com.match.admin.service.impl;

import com.match.admin.dao.MatchesMapper;
import com.match.admin.model.Matches;
import com.match.admin.service.MatchesService;
import com.match.admin.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/11/02.
 */
@Service
@Transactional
public class MatchesServiceImpl extends AbstractService<Matches> implements MatchesService {
    @Resource
    private MatchesMapper matchesMapper;

}
