package com.example.baitap.model;

import lombok.Data;

@Data
public class CertificateUpdateDtoRequest {
    private String id;
    private String certificationName;
    private double cost;
    private String category;
}
