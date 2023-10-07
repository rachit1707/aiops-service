package com.vois.poc.service.impl;

import com.vois.poc.service.PerformRestActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PerformRestActionServiceImpl implements PerformRestActionService {
    private final RestTemplate restTemplate;
    public PerformRestActionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void PerformRestAction() {

    }
}
