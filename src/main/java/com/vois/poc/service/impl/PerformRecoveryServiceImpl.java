package com.vois.poc.service.impl;

import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.PerformRecoveryService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PerformRecoveryServiceImpl implements PerformRecoveryService {

    private final RestTemplate restTemplate;
    public PerformRecoveryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     *
     */
    @Override
    public void performRecovery(PredictionModel predictionModel) throws URISyntaxException, InterruptedException {

        log.info(".............................Initiating the recovery process..................................");

        //update the ticket status in prediction table
        var requestEntity = new HttpEntity<PatchDto>(
                PatchDto.builder()
                        .op("update")
                        .key("status")
                        .value("In Progress").build()
        );

        var predictionResponse = restTemplate.exchange(
                new URI("http://localhost:8081/prediction/"+predictionModel.getId()),
                HttpMethod.PUT,
                requestEntity,
                PredictionModel.class
        );
        log.info("Updated status for prediction response {}",predictionResponse.getBody());
        Thread.sleep(3000);
        //update the ticket status in audit table

    }
}
