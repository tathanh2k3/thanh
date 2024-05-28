package com.example.baitap.model;

import lombok.Data;

import java.util.List;
@Data
public class CertificateGetResponse {
    private List<CertificateGetDtoResponese> certificateGetDtoResponeseList;
    private Integer totalPage;
    private Integer totalElements;

    @Data
    public static class CertificateGetDtoResponese {
        private String id;
        private String certificationName;
        private double cost;
        private String category;
    }
}
