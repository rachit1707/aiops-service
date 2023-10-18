package com.vois.poc.service.impl;

import com.vois.poc.model.AuditModel;
import com.vois.poc.model.PredictionModel;
import com.vois.poc.service.PerformDataModellingService;
import com.vois.poc.util.MLPredictionUtil;
import com.vois.poc.util.RcaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PerformDataModellingServiceImpl implements PerformDataModellingService {

    private final RestTemplate restTemplate;

    public PerformDataModellingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AuditModel> getTrainingDataFromAuditService() {
        var response = restTemplate.getForEntity(
                "http://localhost:8081/audit/incidentByTime"
                , AuditModel[].class);
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()){
            throw new RuntimeException("Error while fetching response from Audit Service");
        }else{
            return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
        }
    }

    @Override
    public List<PredictionModel> getPredictionFromWorkgroup(List<AuditModel> auditModels) {
        return createPredictionModelFromAuditModel(auditModels);
    }

    private List<PredictionModel> createPredictionModelFromAuditModel(List<AuditModel> auditModels){
        List<PredictionModel> predictionModels = new ArrayList<>();
        for(AuditModel auditModel : auditModels){
            predictionModels.add(
                    PredictionModel.builder()
                            .id(auditModel.getId())
                            .ban(auditModel.getBan())
                            .agentAccepted(false)
                            .taskFlowId("")
                            .rca(getRcaForBan(auditModel.getBan()))
                            .description(auditModel.getDescription())
                            .type(auditModel.getType())
                            .workgroup(auditModel.getWorkgroup())
                            .errorCode(auditModel.getErrorCode())
                            .status(auditModel.getStatus())
                            .recoveryActions(getRecoveryActionFromWorkgroup(getRcaForBan(auditModel.getBan()),auditModel.getWorkgroup()))
                            .createdAt(auditModel.getLoggedAt())
                            .updatedAt(Instant.now())
                            .build()
            );
        }
        return predictionModels;
    }

    private String getRcaForBan(String ban) {
        RcaUtil.initiateRCAData();
        return RcaUtil.getRcaForBan(ban);
    }

    private String getRecoveryActionFromWorkgroup(String rca,String workgroup) {
        MLPredictionUtil.initPredictionData();
        if("orderWorkgroup".equals(workgroup) && rca.toLowerCase().contains("phone")){
            return "telephoneFix";
        }else if("orderWorkgroup".equals(workgroup) && rca.toLowerCase().contains("email")){
            return "emailFix";
        }else if("orderWorkgroup".equals(workgroup) && rca.toLowerCase().contains("due date")){
            return "dueDateFix";
        }else{
            return MLPredictionUtil.getPredictionByWorkgroup(workgroup);
        }
    }
}
