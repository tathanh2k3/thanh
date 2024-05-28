package com.example.baitap.controller;

import com.example.baitap.database.entity.Certificate;
import com.example.baitap.model.CertificateGetResponse;
import com.example.baitap.model.CertificatePostDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoRequest;
import com.example.baitap.model.CertificateUpdateDtoResponse;
import com.example.baitap.service.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CertificateController {

    @Autowired
    private CertificateService certificateService;
    @GetMapping
    public String homePage(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size){
        CertificateGetResponse certificateGetResponse = certificateService.pageCertificate(page, size);
        model.addAttribute("certificateLists", certificateGetResponse.getCertificateGetDtoResponeseList());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", certificateGetResponse.getTotalPage());
        model.addAttribute("totalElement", certificateGetResponse.getTotalElements());


        model.addAttribute("certificate", new CertificatePostDtoRequest());
        return "home";
    }

    @PostMapping("/saveCertificate")
    public String createCertificate(@ModelAttribute @Valid CertificatePostDtoRequest certificatePostDtoRequest, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "home";
        }
        certificateService.createCertificate(certificatePostDtoRequest);
        return "redirect:/";
    }

    @GetMapping("/deleteCertificate/{id}")
    public String deleteCertificate(@PathVariable(value = "id") String id){
        this.certificateService.deleteCertificate(id);
        return "redirect:/";
    }

    @GetMapping("/showUpdate/{id}")
    public String showUpdate (@PathVariable(value = "id") String id, Model model ) throws Exception {
        CertificateUpdateDtoResponse certificateUpdate = certificateService.findByIdToUpdata(id);
        model.addAttribute("certificate", certificateUpdate);
        return "update";
    }
    @PostMapping("/update")
    public String update (@ModelAttribute CertificateUpdateDtoResponse response) throws Exception {
        CertificateUpdateDtoRequest certificateUpdateDtoRequest = new CertificateUpdateDtoRequest();
        certificateUpdateDtoRequest.setId(response.getId());
        certificateUpdateDtoRequest.setCertificationName(response.getCertificationName());
        certificateUpdateDtoRequest.setCost(response.getCost());
        certificateUpdateDtoRequest.setCategory(response.getCategory());

        certificateService.updateCertificate(certificateUpdateDtoRequest, response.getId());
        return "redirect:/";
    }


}
