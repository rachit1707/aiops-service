package com.vois.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditModel {
    private String id;
    private String ban;
    private String description;
    private String type;
    private String errorCode;
    private String status;
    private String workgroup;
    private String recoveryUrl;
    private Instant loggedAt;
    private Instant createdAt;
    private Instant updatedAt;
}
