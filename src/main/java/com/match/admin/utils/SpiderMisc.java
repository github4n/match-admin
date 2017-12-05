package com.match.admin.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderMisc {
    private static String getPanLuResponse(String matchId) throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://live.titan007.com/");
        headerMap.put("User-Agent", "Mozilla/3.0 (compatible; Indy Library)");
        //headerMap.put("HeaderEnd", "CRLF");
        String response = HttpRequest.doGet("http://bf.win007.com/panlu/"+matchId+".htm", headerMap,"GBK");
        return response;
    }

    private static String getMatchesResponse() throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://61.143.225.86:88/");
        headerMap.put("User-Agent", "Mozilla/3.0 (compatible; Indy Library)");
        headerMap.put("HeaderEnd", "CRLF");
        String r = "007" + new Date().getTime();
        String response = HttpRequest.doGet("http://61.143.225.86:88/vbsxml/bfdata.js?r=" + r, headerMap,"GBK");
        return response;
    }

    public static List<Map<String, String>> getAllMatches() throws Exception{
        String response = getMatchesResponse();

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

    public static ArrayList<Map<String, Object>> getPanLu(String matchId) throws Exception {
        String response = getPanLuResponse(matchId);
String panLuPatternStr = "var hometeamid.*?var GoalCn";
        Pattern panLuPattern = Pattern.compile(panLuPatternStr);
        Matcher panLuMatcher = panLuPattern.matcher(response);
        String targetStr = null;
        if (panLuMatcher.find()) {
            targetStr = panLuMatcher.group();
        }
        String[] panLuStrArray = targetStr.split(";");
        String sClass = panLuStrArray[0].split("'")[1];
        String homeId = Misc.getStrByPattern("[1-9]\\d*", panLuStrArray[0]);
        ArrayList<Map<String,Object>> panLuList = new ArrayList<>();
        for(int i = 1;i<panLuStrArray.length-1;i++) {
            String[] temp = panLuStrArray[i].split("\\[")[2].split("\\]")[0].split(",");
            Map<String, Object> map = new HashMap<>();
            for (String str : temp) {
                map.put("matchId", temp[0]);
                map.put("sClass", temp[1]);
                map.put("homeName", temp[4]);
                map.put("guestName", temp[5]);
                map.put("homeId", temp[6]);
                map.put("guestId", temp[7]);
                map.put("homeGoal", temp[8]);
                map.put("guestGoal", temp[9]);
                map.put("homeGoalH", temp[10]);
                map.put("guestGoalH", temp[11]);
            }
            panLuList.add(map);
        }
        return panLuList;
    }

    public static String getMatchesByMatchesIdFromNet(String matchesId) throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://score.365rich.cn/");
        headerMap.put("User-Agent", "Mozilla/3.0 (compatible; Indy Library)");
        headerMap.put("HeaderEnd", "CRLF");
        String response = HttpRequest.doGet("http://1x2.nowscore.com/" + matchesId + ".js", headerMap,"utf-8");

        String gameStr = null;
        String gameDetailStr = null;
        String gamePatternStr = "var game=Array\\(\\\"(.*?)\\\"\\);";
        Pattern gamePattern = Pattern.compile(gamePatternStr);
        Matcher gameMatcher = gamePattern.matcher(response);
        if (gameMatcher.find()) {
            gameStr = gameMatcher.group(0);
        }
        String gameDetailPatternStr = "gameDetail=Array\\(\\\"(.*?)\\\"\\);";
        Pattern gameDetailPattern = Pattern.compile(gameDetailPatternStr);
        Matcher gameDetailMatcher = gameDetailPattern.matcher(response);
        if (gameDetailMatcher.find()) {
            gameDetailStr = gameDetailMatcher.group(0);
        }
        return response;
    }
}
