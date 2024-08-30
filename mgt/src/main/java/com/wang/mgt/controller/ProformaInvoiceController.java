package com.wang.mgt.controller;

import com.wang.mgt.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.dataload.dto.ImportMerchant;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proformalInvoice")
public class ProformaInvoiceController {

    @Autowired MerchantService merchantService;

    @GetMapping
    public ImportMerchant searchProformalInvoice(){
        System.out.println("ProformaInvoiceController searchProformalInvoice ");
        ImportMerchant importMerchant = merchantService.ListImporterMerchant(118);

        return importMerchant;

    }
}
