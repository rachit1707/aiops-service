package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.UpdatePredictionService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdatePredictionDataWithStatus implements JavaDelegate {

    private final UpdatePredictionService service;

    public UpdatePredictionDataWithStatus(UpdatePredictionService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var predictedData = (PredictionModel)execution.getVariable("predictionData");
        log.info("Following is the predicted data {}",predictedData);
        var data = service.patchPredictionData(predictedData);
    }
}
