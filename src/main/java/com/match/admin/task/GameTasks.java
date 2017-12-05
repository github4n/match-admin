package com.match.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.match.admin.util.EmailMisc;
import com.match.admin.util.SpiderMisc;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GameTasks {
    private String task1EmailStr = "";

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void task1() throws Exception {
        System.out.println(new Date());
        ArrayList<HashMap<String, String>> matchIdList = SpiderMisc.get70minMatchId();
        System.out.println(String.format("70min比赛：%s", matchIdList));
        for (Map<String, String> temp : matchIdList) {
            ArrayList<Integer> noGoalList = SpiderMisc.getNoGoalCount(temp.get("matchId"));
            System.out.println(noGoalList);
            if (noGoalList.get(0) >= 3 || noGoalList.get(1) >= 3) {
                task1EmailStr = String.format("%s<p>%s</p><p>%s</p><p>%s</p><p></p>", task1EmailStr, temp.get("league"), temp.get("matchTeam"), temp.get("matchGoal"));
            } else {
                continue;
            }
        }
        if (!"".equals(task1EmailStr)) {
            EmailMisc.sendEmail("70min match", task1EmailStr);
            task1EmailStr = "";
        }
    }
}
