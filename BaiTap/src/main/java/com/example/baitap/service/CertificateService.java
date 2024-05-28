package com.example.baitap.service;

import com.example.baitap.database.entity.Certificate;
import com.example.baitap.model.CertificateGetResponse;
import com.example.baitap.model.CertificatePostDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoResponse;

import java.util.List;

public interface CertificateService {
    void createCertificate(CertificatePostDtoRequest request);
    void deleteCertificate(String id);

    void updateCertificate(CertificateUpdateDtoRequest request, String id) throws Exception;

    CertificateGetResponse pageCertificate(int page, int size);

     List<Certificate> list();

    CertificateUpdateDtoResponse findByIdToUpdata(String id) throws Exception;
}
