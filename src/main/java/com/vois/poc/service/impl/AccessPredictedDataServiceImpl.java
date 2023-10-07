package com.vois.poc.service.impl;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.AccessPredictedDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AccessPredictedDataServiceImpl implements AccessPredictedDataService {
    private final RestTemplate restTemplate;

    public AccessPredictedDataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PredictionModel> fetchLatestPredictedData() throws URISyntaxException {
        URI uri = new URI("http://localhost:8081/prediction/getLatestData");
        var response = restTemplate.getForEntity(uri,PredictionModel[].class);
        log.info("Response data {}",response);
        return Objects.nonNull(response.getBody()) ? Arrays.asList(response.getBody()): null;
    }
}
