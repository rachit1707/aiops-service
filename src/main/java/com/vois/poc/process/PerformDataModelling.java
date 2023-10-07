package com.vois.poc.process;

import com.vois.poc.service.PerformDataModellingService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class PerformDataModelling implements JavaDelegate {

    private final PerformDataModellingService service;

    public PerformDataModelling(PerformDataModellingService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
          var data = service.getTrainingDataFromAuditService();
          var predictionData = service.getPredictionFromWorkgroup(data);
          delegateExecution.setVariable("predictionData",predictionData);
    }
}
