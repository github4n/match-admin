package com.match.admin.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailMiscTest {
    @Test
    public void sendEmail() throws Exception {
        EmailMisc.sendEmail("十一号六点比赛","<p>a队 vs b队</p><p>0:0</p>");
    }

}