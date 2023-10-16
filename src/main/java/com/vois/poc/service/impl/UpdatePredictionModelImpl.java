package com.vois.poc.service.impl;

import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.UpdatePredictionService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Patch;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class UpdatePredictionModelImpl implements UpdatePredictionService {
    private final RestTemplate restTemplate;

    public UpdatePredictionModelImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void updatePredictions(List<PredictionModel> predictionModels) throws URISyntaxException {
        URI uri = new URI("http://localhost:8081/prediction");
        //add rca to prediction data
        restTemplate.postForEntity(uri,predictionModels,List.class);
    }

    @Override
    @Synchronized
    public PredictionModel patchPredictionData(PredictionModel predictionModel) throws MalformedURLException, URISyntaxException {
        HttpEntity<PatchDto> requestEntityStatusChange =null;
        final HttpEntity<PatchDto> requestEntityAgentAccepted = new HttpEntity<>(
                PatchDto.builder()
                        .op("update")
                        .key("agentAccepted")
                        .value(Boolean.toString(predictionModel.isAgentAccepted())).build()
        );
        if(predictionModel.getStatus().equals("Creating Incident")){
            requestEntityStatusChange = new HttpEntity<>(
                    PatchDto.builder()
                            .op("update")
                            .key("status")
                            .value("Rejected").build()
            );

        }else{
            requestEntityStatusChange = new HttpEntity<>(
                    PatchDto.builder()
                            .op("update")
                            .key("status")
                            .value("Resolved").build()
            );
        }
        var responseForAgentAccepted = restTemplate.exchange(
                new URL("http://localhost:8081/prediction/"+predictionModel.getId()).toURI(),
                HttpMethod.PUT,
                requestEntityAgentAccepted,PredictionModel.class
        );
        var responseForStatusChange = restTemplate.exchange(
                new URI("http://localhost:8081/prediction/"+predictionModel.getId()),
                HttpMethod.PUT,
                requestEntityStatusChange,
                PredictionModel.class
        );
        if(responseForAgentAccepted.getStatusCode().is2xxSuccessful() && responseForStatusChange.getStatusCode().is2xxSuccessful()){
            return responseForStatusChange.getBody();
        }
        else{
           return null;
        }
    }
}
