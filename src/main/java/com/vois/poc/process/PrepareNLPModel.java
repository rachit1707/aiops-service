package com.vois.poc.process;

import com.vois.poc.service.PrepareNLPService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class PrepareNLPModel implements JavaDelegate {

    private final PrepareNLPService service;

    public PrepareNLPModel(PrepareNLPService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        service.generateModel();
    }
}
