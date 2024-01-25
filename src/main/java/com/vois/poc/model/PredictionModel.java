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
    private String description;
    private String GSL;
    private String createdDate;
    private String status;
    private String category;
    private String action;
    private String ETA;
    private String priority;
    private String comments;
    private String taskFlowId;
    private boolean agentAccepted;
    private String recoveryActions;
    private Instant updatedAt;
    private Instant createdAt;
}
