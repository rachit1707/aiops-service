package com.vois.poc.process;

import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
@Slf4j
public class UpdateTicketToPrediction implements JavaDelegate {

    private final RestTemplate restTemplate;

    public UpdateTicketToPrediction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Updating the Jira ticket id to the Prediction Service");

        String ticketId = (String) delegateExecution.getVariable("ticket_id");
        String incidentId = (String) delegateExecution.getVariable("incidentNumber");

        final HttpEntity<PatchDto> requestEntityAgentAccepted = new HttpEntity<>(
                PatchDto.builder()
                        .op("update")
                        .key("ticketId")
                        .value(ticketId).build()
        );

        var response = restTemplate.exchange(
                new URL("http://localhost:8081/prediction/"+incidentId).toURI(),
                HttpMethod.PUT,
                requestEntityAgentAccepted, PredictionModel.class);

        if (response.getStatusCode().is2xxSuccessful()){
            log.info("Jira Ticket created and updated for incident {}",incidentId);
        }else{
            log.info("Issue while updating Jira to prediction service");
        }

    }
}
