package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.UpdatePredictionService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UpdatePredictionData implements JavaDelegate {
    private final UpdatePredictionService service;

    public UpdatePredictionData(UpdatePredictionService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var predictionData = (List<PredictionModel>) delegateExecution
                .getVariable("predictionData");
        log.info("Prediction Data {}", predictionData);
        service.updatePredictions(predictionData);
    }
}
