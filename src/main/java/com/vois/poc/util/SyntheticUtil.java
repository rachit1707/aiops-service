package com.vois.poc.util;

import com.vois.poc.model.SyntheticModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SyntheticUtil {

    public static final Map<String, SyntheticModel> syntheticData = new ConcurrentHashMap<>();

    public static void initiateSyntheticData(){
        log.info("..........Initiating the RCA Data......");
        syntheticData.put("INC1001001",createSyntheticData("INC1001001","2323001","404","Product Catalog Not found for product id RA123234"));
        syntheticData.put("INC1001002",createSyntheticData("INC1001002","2323002","400","Unable to process Order with id XC1012"));
        syntheticData.put("INC1001003",createSyntheticData("INC1001003","2323004","500","Internal server Error: NullPointerException encountered while processing order at line 40 (phone number is null)"));
    }

    private static SyntheticModel createSyntheticData(String id,String ban, String errorCode, String error) {

        return SyntheticModel.builder()
                .id(id)
                .ban(ban)
                .error(error)
                .errorCode(errorCode)
                .build();

    }

    public static List<SyntheticModel> getAllSyntheticData(){
        List<SyntheticModel> syntheticModels = new ArrayList<>();
        log.info(syntheticData.toString());
        syntheticData.forEach(
                (k,v)-> {
                    syntheticModels.add(v);
                }
        );
        return syntheticModels;
    }

}
