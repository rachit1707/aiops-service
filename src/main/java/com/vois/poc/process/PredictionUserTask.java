package com.vois.poc.process;

import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.PredictionUserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class PredictionUserTask implements TaskListener {
    private final PredictionUserTaskService service;

    public PredictionUserTask(PredictionUserTaskService service) {
        this.service = service;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("------------------Task Id {}",delegateTask);
        var predictionData = (PredictionModel)delegateTask.getExecution().getVariable("predictionData");
        try {
            service.setPredictionDataForUserTask(delegateTask.getId(),predictionData);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
