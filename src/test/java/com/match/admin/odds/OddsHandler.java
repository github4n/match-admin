package com.match.admin.odds;

import com.alibaba.fastjson.JSON;
import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import org.junit.Test;
import project.Tester;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.*;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class OddsHandler extends Tester{

    @Resource
    private OddsService oddsService;

    @Test
    public void oddsHandler() {
        long start = Instant.now().getEpochSecond();
        Condition condition = new Condition(Odds.class);
        condition.setOrderByClause("time");
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("matchsId","1368235");
        List<Odds> list = oddsService.findByCondition(condition);

        System.out.println(list.size());

        Set<Date> timeSet = new LinkedHashSet<>();
        Set<Object> companyIdSet = new HashSet<>();
        Map<Object, List<Object>> companyWinDataMap = new HashMap<>();

        for (Odds odds : list) {
            timeSet.add(odds.getTime());
            companyIdSet.add(odds.getCompanysId());
            if (!companyWinDataMap.containsKey(odds.getCompanysId())) {
                companyWinDataMap.put(odds.getCompanysId(), new ArrayList<>());
            }
        }

        for (List<Object> companyWinData : companyWinDataMap.values()) {
            for(int i = 0;i<timeSet.size();i++) {
                companyWinData.add("");
            }
        }

        for (Odds odds : list) {
            List<Object> companyWinData = companyWinDataMap.get(odds.getCompanysId());
            int i = 0;
            for (Date time : timeSet) {
                if (time.equals(odds.getTime())) {
                    for(int j = i;j<timeSet.size();j++) {
                        companyWinData.set(j, odds.getWin());
                    }
                    break;
                }
                i++;
            }
        }
        System.out.println(Instant.now().getEpochSecond() - start);
        System.out.println(start);
    }
}
