package com.match.admin.web;


import com.alibaba.fastjson.JSON;
import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Marco on 2017/10/30 0030.
 */
@Controller
public class PageController {
    @Resource
    private OddsService oddsService;

    private String userName = "Marco";

    @RequestMapping(value = "/")
    public String pageOne(ModelMap map){
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", userName);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }

    @GetMapping(value = "/page/test")
    public String test(ModelMap map){
        Condition condition = new Condition(Odds.class);
        condition.setOrderByClause("time");
        Example.Criteria criteria = condition.createCriteria();
        String matchsId = "1197763";
        criteria.andEqualTo("matchsId",matchsId);
        criteria.andEqualTo("companyId", "281");//Bet 365

        Example.Criteria criteria2 = condition.createCriteria();
        criteria2.andEqualTo("matchsId",matchsId);
        criteria2.andEqualTo("companyId", "81");//Vcbet
        condition.or(criteria2);

        Example.Criteria criteria3 = condition.createCriteria();
        criteria3.andEqualTo("matchsId",matchsId);
        criteria3.andEqualTo("companyId", "16");//10BET
        condition.or(criteria3);

        Example.Criteria criteria4 = condition.createCriteria();
        criteria4.andEqualTo("matchsId",matchsId);
        criteria4.andEqualTo("companyId", "571");//Jetbull
        condition.or(criteria4);

//        criteria.orEqualTo("companyId", "463");
//        criteria.orEqualTo("companyId", "474");
//        criteria.orEqualTo("companyId", "709");
//        criteria.orEqualTo("companyId", "494");
//        criteria.orEqualTo("companyId", "828");
//        criteria.orEqualTo("companyId", "462");
//        criteria.orEqualTo("companyId", "530");
//        criteria.orEqualTo("companyId", "1017");
//        criteria.orEqualTo("companyId", "1098");
//        criteria.orEqualTo("companyId", "1132");
        List<Odds> list = oddsService.findByCondition(condition);

        Set<Date> timeSet = new LinkedHashSet<>();
        Set<String> companyIdSet = new HashSet<>();
        Map<String, List<Object>> companyWinDataMap = new HashMap<>();
        Map<String, List<Object>> companyDrawDataMap = new HashMap<>();
        Map<String, List<Object>> companyLoseDataMap = new HashMap<>();

        for (Odds odds : list) {
            timeSet.add(odds.getTime());
            companyIdSet.add(odds.getCompanyId().toString());
            if (!companyWinDataMap.containsKey(odds.getCompanyId().toString())) {
                companyWinDataMap.put(odds.getCompanyId().toString(), new ArrayList<>());
            }
            if (!companyDrawDataMap.containsKey(odds.getCompanyId().toString())) {
                companyDrawDataMap.put(odds.getCompanyId().toString(), new ArrayList<>());
            }
            if (!companyLoseDataMap.containsKey(odds.getCompanyId().toString())) {
                companyLoseDataMap.put(odds.getCompanyId().toString(), new ArrayList<>());
            }
        }

        for (List<Object> companyWinData : companyWinDataMap.values()) {
            for(int i = 0;i<timeSet.size();i++) {
                companyWinData.add("");
            }
        }
        for (List<Object> companyDrawData : companyDrawDataMap.values()) {
            for(int i = 0;i<timeSet.size();i++) {
                companyDrawData.add("");
            }
        }
        for (List<Object> companyLoseData : companyLoseDataMap.values()) {
            for(int i = 0;i<timeSet.size();i++) {
                companyLoseData.add("");
            }
        }

        for (Odds odds : list) {
            List<Object> companyWinData = companyWinDataMap.get(odds.getCompanyId().toString());
            List<Object> companyDrawData = companyDrawDataMap.get(odds.getCompanyId().toString());
            List<Object> companyLoseData = companyLoseDataMap.get(odds.getCompanyId().toString());
            int i = 0;
            for (Date time : timeSet) {
                if (time.equals(odds.getTime())) {
                    for(int j = i;j<timeSet.size();j++) {
                        companyWinData.set(j, odds.getWin());
                        companyDrawData.set(j, odds.getDraw());
                        companyLoseData.set(j, odds.getLose());
                    }
                    break;
                }
                i++;
            }
        }

        Map<String, Object> option = new HashMap<>();

        String titleStr = "{'text': 'matchsId:" + matchsId + "','subtext': 'test'}";
        Map<String, Object> title = JSON.parseObject(titleStr, HashMap.class);
        option.put("title", title);

        String tooltipStr = "{'trigger': 'axis'}";
        Map<String, Object> tooltip = JSON.parseObject(tooltipStr, HashMap.class);
        option.put("tooltip", tooltip);

        String toolboxStr = "{show: true,feature: {dataZoom: {yAxisIndex: 'none'},dataView: {readOnly: false},magicType: {type: ['line', 'bar']},restore: {},saveAsImage: {}}}";
        Map<String, Object> toolbox = JSON.parseObject(toolboxStr, HashMap.class);
        option.put("toolbox", toolbox);

        String yAxisStr = "{type: 'value'}";
        Map<String, Object> yAxis = JSON.parseObject(yAxisStr, HashMap.class);
        option.put("yAxis", yAxis);

        String legendStr = "{data:[]}";
        Map<String, Object> legend = JSON.parseObject(legendStr, HashMap.class);
        List<String> legendData = (List<String>)legend.get("data");
        for (String companyId : companyIdSet) {
            legendData.add(companyId+"W");
            legendData.add(companyId+"D");
            legendData.add(companyId+"L");
        }
        option.put("legend", legend);

        String xAxisStr = "{type: 'category',boundaryGap: false,data: []}";
        Map<String,Object> xAxis = JSON.parseObject(xAxisStr, HashMap.class);
        List<String> xAxisData = (List<String>)xAxis.get("data");
//        for(int i = 0;i<timeSet.size();i++) {
//            xAxisData.add(String.valueOf(i));
//        }
        for (Date date : timeSet) {
            xAxisData.add(date.toString());
        }
        option.put("xAxis", xAxis);

        List<Object> series = new ArrayList<>();
        for (String companyId : companyIdSet) {
            Map<String, Object> seriesMap = new HashMap<>();
            seriesMap.put("name", companyId+"W");
            seriesMap.put("type", "line");
            seriesMap.put("data", companyWinDataMap.get(companyId));
            series.add(seriesMap);
        }
        for (String companyId : companyIdSet) {
            Map<String, Object> seriesMap = new HashMap<>();
            seriesMap.put("name", companyId+"D");
            seriesMap.put("type", "line");
            seriesMap.put("data", companyDrawDataMap.get(companyId));
            series.add(seriesMap);
        }
        for (String companyId : companyIdSet) {
            Map<String, Object> seriesMap = new HashMap<>();
            seriesMap.put("name", companyId+"L");
            seriesMap.put("type", "line");
            seriesMap.put("data", companyLoseDataMap.get(companyId));
            series.add(seriesMap);
        }
        option.put("series", series);

        map.put("option", JSON.toJSON(option).toString());
        return "page/test";
    }
}
