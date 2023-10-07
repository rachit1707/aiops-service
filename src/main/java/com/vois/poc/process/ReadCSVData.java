package com.vois.poc.process;

import com.vois.poc.service.ReadCsvDataService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReadCSVData implements JavaDelegate {
    private final ReadCsvDataService service;

    public ReadCSVData(ReadCsvDataService service) {
        this.service = service;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //read data from csv
        var csvData = service.readCsvDataToArray("src/main/resources/csv/incident.csv");
        delegateExecution.setVariable("csvData",csvData);
        //add workgroup for incidents
    }
}
