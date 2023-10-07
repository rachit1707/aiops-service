package com.vois.poc.service.impl;

import com.vois.poc.model.AuditModel;
import com.vois.poc.service.UpdateWorkgroupService;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UpdateWorkgroupServiceImpl implements UpdateWorkgroupService {

    private final RestTemplate restTemplate;

    public UpdateWorkgroupServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String[]> updateWorkGroup(List<String[]> csvData) throws IOException {
        for(int i=1;i<csvData.size();i++){
            csvData.get(i)[6] = getWorkgroupByDescription(csvData.get(i)[2]);
            csvData.get(i)[5] = "Open ";
        }
        log.info("Csv Data Prepared with workflow {}",csvData);
        return csvData;
    }
    private String getWorkgroupByDescription(String description) throws IOException {
        File file = ResourceUtils.getFile("src/main/resources/nlp-model/en-trained-model.bin");
        InputStream in = new FileInputStream(file);
        DoccatModel m = new DoccatModel(in);
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(m);
        String[] inputText = description.split(" ");
        double[] outcomes = myCategorizer.categorize(inputText);
        return myCategorizer.getBestCategory(outcomes);
    }

    @Override
    public void updateAuditServiceWithLatestData(List<String[]> csvData) throws URISyntaxException {
        //update audit service with the data
        List<AuditModel> auditModel = prepareAuditModelFromCsvData(csvData);
        URI uri = new URI("http://localhost:8081/audit");
        restTemplate.postForEntity(uri,auditModel,List.class);
    }

    private List<AuditModel> prepareAuditModelFromCsvData(List<String[]> csvData) {
        //prepare Audit model
        List<AuditModel> auditModelList = new ArrayList<>();
        for(int i=1;i<csvData.size();i++) {
            auditModelList.add(AuditModel.builder()
                    .id(csvData.get(i)[0])
                            .ban(csvData.get(i)[1])
                    .description(csvData.get(i)[2])
                    .type(csvData.get(i)[3])
                    .errorCode(csvData.get(i)[4])
                    .status(csvData.get(i)[5])
                    .workgroup(csvData.get(i)[6])
                    .loggedAt(Instant.parse(csvData.get(i)[7]))
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now()).build());
        }
        return auditModelList;
    }
}
