package com.vois.poc.process;

import com.vois.poc.service.UpdateWorkgroupService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UpdateWorkgroup implements JavaDelegate {

    private final UpdateWorkgroupService service;

    public UpdateWorkgroup(UpdateWorkgroupService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var csvData = (List<String[]>)delegateExecution.getVariable("csvData");
        var data = service.updateWorkGroup(csvData);
        service.updateAuditServiceWithLatestData(data);
    }
}
