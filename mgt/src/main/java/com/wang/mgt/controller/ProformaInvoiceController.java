package com.wang.mgt.controller;

import com.wang.dataload.dto.ProformaInvoiceDTO;
import com.wang.dataload.dto.ProformaInvoiceOrderWithItem;
import com.wang.mgt.service.MerchantService;
import com.wang.mgt.service.ProformaInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.dataload.dto.ImportMerchant;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proformalInvoice")
public class ProformaInvoiceController {

    @Autowired MerchantService merchantService;

    @Autowired
    ProformaInvoiceService proformaInvoiceService;

    @GetMapping("/findMerchantByNum")
    public ImportMerchant searchMerchant(){
        System.out.println("ProformaInvoiceController searchMerchant ");
        ImportMerchant importMerchant = merchantService.listImporterMerchant(118);

        return importMerchant;
    }

    @GetMapping("/findMerchant")
    public ImportMerchant getMerchant(){
        System.out.println("ProformaInvoiceController getMerchant ");
        ImportMerchant importMerchant = new ImportMerchant();
        importMerchant.setMerchantName("france");
        importMerchant.setMerchantAddress("urvan visat");


        return importMerchant;
    }

    @GetMapping("/findProformaInvoiceByNum")
    public ProformaInvoiceDTO searchProformaInvoice(){
        System.out.println("ProformaInvoiceController searchProformaInvoice ");
        ProformaInvoiceDTO proformaInvoiceDTO = proformaInvoiceService.searchProformaInvoiceByInvoiceNo("22W-012");
        System.out.println("proformaInvoiceDTO " + proformaInvoiceDTO.toString());
        return proformaInvoiceDTO;
    }

    @GetMapping("/findProformaInvoiceById")
    public List<ProformaInvoiceOrderWithItem>   searchProformaInvoiceById(){
        System.out.println("ProformaInvoiceController searchProformaInvoiceById ");
        List<ProformaInvoiceOrderWithItem>  proformaInvoiceOrderWithItemList = proformaInvoiceService.selectProformaInvoiceWithItems(1);
        System.out.println("proformaInvoiceOrderWithItemList " + proformaInvoiceOrderWithItemList.toString());
        return proformaInvoiceOrderWithItemList;
    }


}
