package com.vois.poc.service.impl;

import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.CreateJiraIncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
@Slf4j
public class CreateJiraIncidentServiceImpl implements CreateJiraIncidentService {

    private final RestTemplate restTemplate;

    public CreateJiraIncidentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean createIncident(PredictionModel predictionModel) throws URISyntaxException, InterruptedException {
        log.info("............................Creating JIRA incident for incident id {}..............................",predictionModel.getId());

        var requestEntity = new HttpEntity<>(
                PatchDto.builder()
                        .op("update")
                        .key("status")
                        .value("Creating Workflow").build()
        );

        var response = restTemplate.exchange(
                new URI("http://localhost:8081/prediction/"+predictionModel.getId()),
                HttpMethod.PUT,
                requestEntity,
                PredictionModel.class
        );
        predictionModel.setStatus("Creating Workflow");
        Thread.sleep(5000);
        if(response.getStatusCode().is2xxSuccessful()){
          return true;
        }else{
            return false;
        }
    }
}
