package com.match.admin.util;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestTest {
    @Test
    public void doGet() throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://61.143.225.86:88/");
        headerMap.put("User-Agent", "Mozilla/3.0 (compatible; Indy Library)");
        headerMap.put("HeaderEnd", "CRLF");
        String r = "007" + new Date().getTime();
        String response = HttpRequest.doGet("http://61.143.225.86:88/vbsxml/bfdata.js?r=" + r, headerMap,"GBK");
        System.out.println(response);
    }

}
