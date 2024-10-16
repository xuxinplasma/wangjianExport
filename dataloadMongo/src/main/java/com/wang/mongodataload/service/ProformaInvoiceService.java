package com.wang.mongodataload.service;

import com.wang.mongodataload.dto.ProformaInvoiceDTO;
import com.wang.mongodataload.repository.ProformaInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProformaInvoiceService {

    @Autowired
    private ProformaInvoiceRepository proformaInvoiceRepository;

    public ProformaInvoiceDTO save(ProformaInvoiceDTO proformaInvoiceDTO){
        return proformaInvoiceRepository.save(proformaInvoiceDTO);

    }
}
