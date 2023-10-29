package com.vois.poc.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RcaUtil {
    public static final Map<String, String> rcaData = new ConcurrentHashMap<>();

    public static void initiateRCAData(){
        log.info("..........Initiating the RCA Data......");

        rcaData.put("45321","Email Id is missing for the order");
        rcaData.put("486232122","Maintanance activity in the area");
        rcaData.put("45678","Indexing Activity was not successful which led to this issue");
        rcaData.put("332304787","Indexing Activity was not successful");
        rcaData.put("PPB03733290","Issue with the phone");
        rcaData.put("PRB0003764","Please ask the agent to update the date");
        rcaData.put("211989057","Please ask the agent to update the phone number for the customer");
        rcaData.put("490279977","Due Date has passed for the order");
        rcaData.put("437067877","Due Date is missing");
        rcaData.put("12222","The role is not found in the Database please add role before providing the access");
        rcaData.put("455666","The user does not have access to the UAT");
        rcaData.put("123456","Deployment Scheduled");
        rcaData.put("1222112","Automation Test scripts were running which led to un-availability of the resources");
        rcaData.put("5465665","User password has expired");
        rcaData.put("233444","Major fix release is provided");
        rcaData.put("2323002","Phone number is missing");
        rcaData.put("2323001","Product information missing in DB");

    }

    public static String getRcaForBan(String ban){
        return rcaData.getOrDefault(ban,"");
    }
}
