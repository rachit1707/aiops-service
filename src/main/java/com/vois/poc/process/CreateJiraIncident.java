package com.vois.poc.process;


import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.CreateJiraIncidentService;
import com.vois.poc.service.PerformRestActionService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateJiraIncident implements JavaDelegate {

    private final CreateJiraIncidentService service;

    public CreateJiraIncident(CreateJiraIncidentService service) {
        this.service = service;
    }
    /**
     * @param delegateExecution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("......................Creating the JIRA incident for the user rejected incident.............................");
        var predictionModel = (PredictionModel)delegateExecution.getVariable("predictionData");
        service.createIncident(predictionModel);
    }
}
