package com.vois.poc.service;

import com.vois.poc.model.PredictionModel;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface PerformRecoveryService {

    void performRecovery(PredictionModel predictionModel) throws URISyntaxException, InterruptedException;

}
