package com.match.admin.utils;

import java.util.*;

public class SpiderMisc {
    public static List<Map<String, String>> getAllMatches() throws Exception{
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://61.143.225.86:88/");
        headerMap.put("User-Agent", "Mozilla/3.0 (compatible; Indy Library)");
        headerMap.put("HeaderEnd", "CRLF");
        String r = "007" + new Date().getTime();
        String response = HttpRequest.doGet("http://61.143.225.86:88/vbsxml/bfdata.js?r=" + r, headerMap,"GBK");

        response = response.replaceAll("<.*?>", "");
        int matchCount = Integer.parseInt(response.split(";")[3].split("=")[1]);
        String[] tempMatchInfoString = response.split("\\);");

        List<Map<String,String>> matchesList = new ArrayList<>();

        for (int i = 3; i < (matchCount + 3); i++) {
            String[] temp = tempMatchInfoString[i].split("\"");
            String matchStr ;
            if (i == 3) {
                matchStr = temp[5];
            } else {
                matchStr = temp[1];
            }
            String[] matchList = matchStr.split("\\^");
            Map<String, String> match = new HashMap<>();
            match.put("matchId",matchList[0]);
            match.put("league",matchList[2]);
            match.put("matchTeam",matchList[5] + " VS " + matchList[8]);
            matchesList.add(match);
        }
        return matchesList;
    }
}
