package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.PerformRecoveryService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PerformRecovery implements JavaDelegate {


    private final PerformRecoveryService service;

    public PerformRecovery(PerformRecoveryService service) {
        this.service = service;
    }
    /**
     * @param delegateExecution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Service task : {}",delegateExecution.getCurrentActivityName());
        var predictionData = (PredictionModel) delegateExecution.getVariable("predictionData");
        service.performRecovery(predictionData);
    }
}
