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
    private String description;
    private String GSL;
    private String createdDate;
    private String status;
    private String category;
    private String action;
    private String ETA;
    private String priority;
    private String comments;
    private Instant updatedAt;
    private Instant createdAt;
}
