package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetAccessRequest implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Starting GetAccessRequest with access data {}",delegateExecution.getVariable("accessData"));
        PredictionModel predictionModel = (PredictionModel) delegateExecution.getVariable("accessData");
        delegateExecution.setVariable("email",predictionModel.getNotes().get("user"));
        delegateExecution.setVariable("adType",predictionModel.getNotes().get("accessType"));
        delegateExecution.setVariable("country",predictionModel.getCountry());
    }
}
