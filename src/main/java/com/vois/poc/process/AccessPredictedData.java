package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.AccessPredictedDataService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccessPredictedData implements JavaDelegate {
    private final AccessPredictedDataService service;

    public AccessPredictedData(AccessPredictedDataService service) {
        this.service = service;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Fetching the latest Predicted Data");
        List<PredictionModel> predictionData = service.fetchLatestPredictedData();
        delegateExecution.setVariable("latestPredictionData",predictionData);
    }
}
