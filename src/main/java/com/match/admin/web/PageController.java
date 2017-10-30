package com.match.admin.web;


import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String,Object> mapp = new HashMap<>();
        mapp.put("matchs_id","1197760");
        map.addAttribute("name","test");
        return "page/test";
    }
}
