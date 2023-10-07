package com.vois.poc.service;

import com.vois.poc.model.PredictionModel;

import java.net.URISyntaxException;

public interface CreateJiraIncidentService {

    boolean createIncident(PredictionModel predictionModel) throws URISyntaxException, InterruptedException;

}
