package com.vois.poc.util;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MLPredictionUtil {

    public static final Map<String, String> predictionData = new ConcurrentHashMap<>();

    public static void initPredictionData(){
        log.info("..........Initiating the prediction data form ML......");
        predictionData.put(
                "networkWorkgroup","networkFix"
        );
        predictionData.put(
                "orderWorkgroup","orderFix"
        );
        predictionData.put(
                "productWorkgroup","productFix"
        );
        predictionData.put(
                "FIN-X-Performance","performanceFix"
        );
        predictionData.put(
                "FinX-L1-OPS-DEP","deploymentExecution"
        );
        predictionData.put(
                "FinX-L1-OPS-ACC","finXAccountFix"
        );
       //add prediction data based on error code and description of the incidents

    }


    public static String getPredictionByWorkgroup(String workgroup){
       return predictionData.getOrDefault(workgroup, "No Recovery Found");
    }

}
