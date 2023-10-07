package com.vois.poc.service;

import com.vois.poc.model.PredictionModel;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface AccessPredictedDataService {

    List<PredictionModel> fetchLatestPredictedData() throws URISyntaxException;

}
