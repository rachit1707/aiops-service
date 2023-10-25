package com.vois.poc.controller;

import com.vois.poc.service.ReadCsvDataService;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/aiops")
public class AiopsController {

    private final RuntimeService service;
    private final ReadCsvDataService readCsvDataService;

    public AiopsController(RuntimeService service, ReadCsvDataService readCsvDataService) {
        this.service = service;
        this.readCsvDataService = readCsvDataService;
    }

    @GetMapping("/{processName}")
    public void prepareNLPModel(@PathVariable("processName")String processName) throws IOException {
        service.startProcessInstanceByKey(processName);
    }

    @GetMapping("/bulkRunProcess")
    public void bulkProcessExecution() throws InterruptedException {
        service.startProcessInstanceByKey("nlp-model");
        Thread.sleep(1000);
        service.startProcessInstanceByKey("csv-processor");
        Thread.sleep(1000);
        service.startProcessInstanceByKey("perform-modelling");
        Thread.sleep(1000);
        service.startProcessInstanceByKey("main-process");
    }

    @GetMapping("/workgroup")
    public ResponseEntity<String> getWorkgroup(@RequestBody String desc) throws IOException {
        File file = ResourceUtils.getFile("src/main/resources/nlp-model/en-trained-model.bin");
        InputStream in = new FileInputStream(file);
        DoccatModel m = new DoccatModel(in);
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(m);
        String[] inputText = desc.split(" ");
        double[] outcomes = myCategorizer.categorize(inputText);
        return ResponseEntity.ok(myCategorizer.getBestCategory(outcomes));
    }
}
