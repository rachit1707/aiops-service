package com.vois.poc.service;

import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface UpdatePredictionService {
    void updatePredictions(List<PredictionModel> predictionModels) throws URISyntaxException;
    PredictionModel patchPredictionData(PredictionModel predictionModel) throws MalformedURLException, URISyntaxException;
}
