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
        criteria.andEqualTo("matchsId","1368235");
        List<Odds> list = oddsService.findByCondition(condition);

        Map<String, Object> option = new HashMap<>();

        String titleStr = "{'text': '未来一周气温变化','subtext': '纯属虚构'}";
        Map<String, Object> title = JSON.parseObject(titleStr, HashMap.class);
        option.put("title", title);

        String tooltipStr = "{'trigger': 'axis'}";
        Map<String, Object> tooltip = JSON.parseObject(tooltipStr, HashMap.class);
        option.put("tooltip", tooltip);

        String toolboxStr = "{show: true,feature: {dataZoom: {yAxisIndex: 'none'},dataView: {readOnly: false},magicType: {type: ['line', 'bar']},restore: {},saveAsImage: {}}}";
        Map<String, Object> toolbox = JSON.parseObject(toolboxStr, HashMap.class);
        option.put("toolbox", toolbox);

        String yAxisStr = "{type: 'value',axisLabel: {formatter: '{value}'}}";
        Map<String, Object> yAxis = JSON.parseObject(yAxisStr, HashMap.class);
        option.put("yAxis", yAxis);

        Set<String> timeSet= new HashSet<>();
        Set<String> companySet = new HashSet<>();

        for (Odds odds : list) {
            timeSet.add(odds.getTime().toString());
            companySet.add(odds.getCompanysId().toString());
        }
        map.put("count", list.size());
        System.out.println(JSON.toJSON(timeSet));
        return "page/test";
    }
}
