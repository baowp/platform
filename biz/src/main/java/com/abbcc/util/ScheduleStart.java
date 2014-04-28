package com.abbcc.util;

import java.io.InputStream;
import java.net.URL;

public class ScheduleStart {
	public void startScheduleXCLSSForInvoicePrintFromWeb(String url,String functionName) {
        try {
            String flag = "false";
            //CheckCgScheduleFlag scheduleFlag = new CheckCgScheduleFlag();
            //flag = scheduleFlag.startScheduleFlag(functionName);
            URL o_url = new URL(url + "&flag=" + flag);
            InputStream in = o_url.openStream();
            in.close();
            
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
