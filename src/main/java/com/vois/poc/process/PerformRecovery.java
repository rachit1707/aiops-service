package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.PerformRecoveryService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PerformRecovery implements JavaDelegate {


    private final PerformRecoveryService service;
    private final RuntimeService runtimeService;

    public PerformRecovery(PerformRecoveryService service, RuntimeService runtimeService) {
        this.service = service;
        this.runtimeService = runtimeService;
    }
    /**
     * @param delegateExecution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Service task : {}",delegateExecution.getCurrentActivityName());
        var predictionData = (PredictionModel) delegateExecution.getVariable("predictionData");
        runtimeService.startProcessInstanceByKey("access_recovery", Map.of("accessData",predictionData));
        service.performRecovery(predictionData);
    }
}
