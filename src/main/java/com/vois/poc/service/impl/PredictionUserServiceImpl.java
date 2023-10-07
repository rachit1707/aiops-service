package com.vois.poc.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vois.poc.model.PatchDto;
import com.vois.poc.model.PredictionModel;
import com.vois.poc.model.UserPredictionModel;
import com.vois.poc.service.PredictionUserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PredictionUserServiceImpl implements PredictionUserTaskService {

    private final RestTemplate restTemplate;

    public PredictionUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void setPredictionDataForUserTask(String taskFlowId, PredictionModel predictionModel) throws URISyntaxException, MalformedURLException {
        //UserPredictionModel userPredictionModel = createUserPredictionModel(taskFlowId,predictionModel);
        predictionModel.setTaskFlowId(taskFlowId);
        log.info("Prediction models created {}",predictionModel);
        final HttpEntity<PatchDto> requestEntity = new HttpEntity<>(
                PatchDto.builder()
                        .op("update")
                        .key("taskFlowId")
                        .value(predictionModel.getTaskFlowId()).build()
        );
        var response = restTemplate.exchange(
                new URL("http://localhost:8081/prediction/"+predictionModel.getId()).toURI(),
                HttpMethod.PUT,
                requestEntity,PredictionModel.class
        );
        log.info("Response {}",response);
    }

//    private UserPredictionModel createUserPredictionModel(String taskFlowId,PredictionModel predictionModel) {
//        return UserPredictionModel.builder()
//                .id(taskFlowId)
//                .ban(predictionModel.getBan())
//                .description(predictionModel.getDescription())
//                .recoveryAction(predictionModel.getRecoveryActions())
//                .incidentId(predictionModel.getId())
//                .status(predictionModel.getStatus())
//                .workgroup(predictionModel.getWorkgroup())
//                .isAgentAccepted(false).build();
//    }


}
