package com.vois.poc.service;

import com.vois.poc.model.PredictionModel;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface PredictionUserTaskService {

    void setPredictionDataForUserTask(String taskFlowId, PredictionModel predictionModels) throws URISyntaxException, MalformedURLException;

}
