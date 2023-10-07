package com.vois.poc.service;

import com.vois.poc.model.AuditModel;
import com.vois.poc.model.PredictionModel;

import java.util.List;

public interface PerformDataModellingService {

    List<AuditModel> getTrainingDataFromAuditService();
    List<PredictionModel> getPredictionFromWorkgroup(List<AuditModel> auditModels);

}
