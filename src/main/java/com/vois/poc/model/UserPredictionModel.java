package com.vois.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPredictionModel {

    private String id;
    private String incidentId;
    private String description;
    private String ban;
    private String recoveryAction;
    private String status;
    private String workgroup;
    private boolean isAgentAccepted;

}
