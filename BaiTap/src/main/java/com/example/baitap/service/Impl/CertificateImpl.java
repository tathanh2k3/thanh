package com.example.baitap.service.Impl;

import com.example.baitap.database.entity.Certificate;
import com.example.baitap.database.repository.CertificateRepository;
import com.example.baitap.model.CertificateGetResponse;
import com.example.baitap.model.CertificatePostDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoResponse;
import com.example.baitap.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificateImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Override
    public void createCertificate(CertificatePostDtoRequest request) {
        Certificate certificate = new Certificate();
        certificate.setId(request.getId());
        certificate.setCertificationName(request.getCertificationName());
        certificate.setCost(request.getCost());
        certificate.setCategory(request.getCategory());
        certificateRepository.save(certificate);
    }

    @Override
    public void deleteCertificate(String id) {
        this.certificateRepository.deleteById(id);
    }

    @Override
    public void updateCertificate(CertificateUpdateDtoRequest request, String id) throws Exception {
        try {
            Optional<Certificate> certificateOptional = certificateRepository.findById(id);
            if (certificateOptional.isEmpty()){
                throw new Exception("Ko Tim Thay");
            }
            Certificate certificate = certificateOptional.get();
            certificate.setId(request.getId());
            certificate.setCertificationName(request.getCertificationName());
            certificate.setCost(request.getCost());
            certificate.setCategory(request.getCategory());
            certificateRepository.save(certificate);

        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public CertificateGetResponse pageCertificate(int page, int size) {
        CertificateGetResponse certificateGetResponse = new CertificateGetResponse();
        Page<Certificate> certificatePage = certificateRepository.findAll(PageRequest.of(page, size));

        List<CertificateGetResponse.CertificateGetDtoResponese> dtoResponeseList = certificatePage.getContent().stream().map(entity -> {
            CertificateGetResponse.CertificateGetDtoResponese dtoResponese = new CertificateGetResponse.CertificateGetDtoResponese();
            dtoResponese.setId(entity.getId());
            dtoResponese.setCertificationName(entity.getCertificationName());
            dtoResponese.setCost(entity.getCost());
            dtoResponese.setCategory(entity.getCategory());
            return dtoResponese;
        }).collect(Collectors.toList());

        certificateGetResponse.setCertificateGetDtoResponeseList(dtoResponeseList);
        certificateGetResponse.setTotalPage(certificatePage.getTotalPages());
        certificateGetResponse.setTotalElements((int) certificatePage.getTotalElements());
        return certificateGetResponse;
    }

    @Override
    public List<Certificate> list() {
        return certificateRepository.findAll();
    }

    @Override
    public CertificateUpdateDtoResponse findByIdToUpdata(String id) throws Exception {
        CertificateUpdateDtoResponse certificateUpdateDtoResponse = new CertificateUpdateDtoResponse();
        try {
            Optional<Certificate> certificateOptional = certificateRepository.findById(id);
            if (certificateOptional.isEmpty()){
                throw new Exception("Ko Tim Thay");
            }
            Certificate certificate = certificateOptional.get();
            certificateUpdateDtoResponse.setId(certificate.getId());
            certificateUpdateDtoResponse.setCertificationName(certificate.getCertificationName());
            certificateUpdateDtoResponse.setCost(certificate.getCost());
            certificateUpdateDtoResponse.setCategory(certificate.getCategory());

        }catch (Exception e){
            throw e;
        }
        return certificateUpdateDtoResponse;
    }
}
