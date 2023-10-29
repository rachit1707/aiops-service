package com.vois.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SyntheticModel {

    private String id;
    private String ban;
    private String errorCode;
    private String error;

}
