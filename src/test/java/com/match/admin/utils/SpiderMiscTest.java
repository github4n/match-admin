package com.match.admin.utils;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SpiderMiscTest {
    @Test
    public void getAllMatches() throws Exception {
        List<Map<String,String>> matchesList = SpiderMisc.getAllMatches();
        System.out.println(matchesList);
    }

    @Test
    public void getMatchesByMatchesIdFromNet() throws Exception {
        String response = SpiderMisc.getMatchesByMatchesIdFromNet("1474197");
        System.out.println(response);
    }

}