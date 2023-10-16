package com.vois.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PredictionModel implements Serializable {
    private String id;
    private String taskFlowId;
    private boolean agentAccepted;
    private String ban;
    private String description;
    private String type;
    private String errorCode;
    private String status;
    private String workgroup;
    private String recoveryActions;
    private String rca;
    private Instant updatedAt;
    private Instant createdAt;
}
