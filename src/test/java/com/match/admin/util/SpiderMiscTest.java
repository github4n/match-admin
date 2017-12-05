package com.match.admin.util;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SpiderMiscTest {
    @Test
    public void getAllMatches() throws Exception {
        List<Map<String,String>> matchesList = SpiderMisc.getAllMatches();
        System.out.println(matchesList);
    }

    @Test
    public void getMatchesByMatchesIdFromNet() throws Exception {
        String response = SpiderMisc.getMatchesByMatchIdFromNet("1474197");
        System.out.println(response);
    }

    @Test
    public void getPanLu() throws Exception {
        System.out.println(SpiderMisc.getNoGoalCount("1461100"));
    }

    @Test
    public void get70minMatchId() throws Exception {
        System.out.println(SpiderMisc.get70minMatchId());
    }

}