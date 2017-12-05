package com.match.admin.web;


import com.alibaba.fastjson.JSON;
import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import com.match.admin.util.SpiderMisc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/matcheslist")
    public String matchesListPage(ModelMap map) throws Exception {
        map.put("matchesList", SpiderMisc.getAllMatches());
        return "page/matchesList";
    }

    @GetMapping(value = "/matchesoddsinfo/{matchesId}")
    public String matchesOddsInfo(ModelMap map, @PathVariable("matchesId") String matchesId) {

        return "page/matchesOddsInfo";
    }

    @GetMapping(value = "/matchesoddsinfofromdatabase/{matchesId}")
    public String matchesOddsInfoFromDatabase(ModelMap map, @PathVariable("matchesId") String matchesId){
        Condition condition = new Condition(Odds.class);
        condition.setOrderByClause("time");

        int[] companyList = {};//{281, 81, 16, 571, 463, 474, 709, 494, 828,462,530,1017,1098,1132};
        if (companyList.length > 0) {
            for (int companyId : companyList) {
                Example.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("matchesId", matchesId);
                criteria.andEqualTo("companyId", companyId);
                condition.or(criteria);
            }
        } else {
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("matchesId", matchesId);
            condition.or(criteria);
        }
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

        Double yMaxValue = 0.0;
        for (Odds odds : list) {
            if (odds.getWin() > yMaxValue) {
                yMaxValue = odds.getWin();
            }
            if (odds.getDraw() > yMaxValue) {
                yMaxValue = odds.getDraw();
            }
            if (odds.getLose() > yMaxValue) {
                yMaxValue = odds.getLose();
            }
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

        Map<String, Object> winOption = getOption(matchesId, "Win", yMaxValue, companyIdSet, timeSet, companyWinDataMap);
        Map<String, Object> drawOption = getOption(matchesId, "Draw", yMaxValue, companyIdSet, timeSet, companyDrawDataMap);
        Map<String, Object> loseOption = getOption(matchesId, "Lose", yMaxValue, companyIdSet, timeSet, companyLoseDataMap);


        map.put("winOption", JSON.toJSON(winOption).toString());
        map.put("drawOption", JSON.toJSON(drawOption).toString());
        map.put("loseOption", JSON.toJSON(loseOption).toString());

        return "page/matchesOddsInfo";
    }

    private Map<String, Object> getOption(String matchesId, String type, Double yMaxValue, Set<String> companyIdSet, Set<Date> timeSet, Map<String, List<Object>> companyDataMap) {
        Map<String, Object> option = new HashMap<>();

        String titleStr = "{'text': '" + type + "','subtext': '" + matchesId + "'}";
        Map<String, Object> title = JSON.parseObject(titleStr, HashMap.class);
        option.put("title", title);

        String tooltipStr = "{'trigger': 'axis'}";
        Map<String, Object> tooltip = JSON.parseObject(tooltipStr, HashMap.class);
        option.put("tooltip", tooltip);

        String toolboxStr = "{show: true,feature: {dataZoom: {yAxisIndex: 'none'},dataView: {readOnly: false},restore: {},saveAsImage: {}}}";
        Map<String, Object> toolbox = JSON.parseObject(toolboxStr, HashMap.class);
        option.put("toolbox", toolbox);

        String yAxisStr = "{type: 'value',scale: false,}";//y坐标脱离0值开始“scale”选值为“true”，否则为“false”
        Map<String, Object> yAxis = JSON.parseObject(yAxisStr, HashMap.class);
        yAxis.put("max", yMaxValue);
        option.put("yAxis", yAxis);

        String legendStr = "{data:[],bottom:10,type:'scroll'}";//普通图例“plain”/滚动图例“scroll”
        Map<String, Object> legend = JSON.parseObject(legendStr, HashMap.class);
        List<String> legendData = (List<String>) legend.get("data");
        for (String companyId : companyIdSet) {
            legendData.add(companyId);
//            legendData.add(companyId+"W");
//            legendData.add(companyId+"D");
//            legendData.add(companyId+"L");
        }
        option.put("legend", legend);

        String xAxisStr = "{type: 'category',boundaryGap: false,data: []}";
        Map<String, Object> xAxis = JSON.parseObject(xAxisStr, HashMap.class);
        List<String> xAxisData = (List<String>) xAxis.get("data");
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
            seriesMap.put("name", companyId);
//            seriesMap.put("name", companyId+"W");
            seriesMap.put("type", "line");
            seriesMap.put("symbol", "diamond");
            seriesMap.put("data", companyDataMap.get(companyId));

            Map<String, Object> lineStyleMap = new HashMap<>();
            Map<String, Object> normalMap = new HashMap<>();
            normalMap.put("type", "solid");//solid/dashed/dotted
            lineStyleMap.put("normal", normalMap);
            seriesMap.put("lineStyle", lineStyleMap);

            series.add(seriesMap);
        }
        option.put("series", series);

        return option;
    }
}
