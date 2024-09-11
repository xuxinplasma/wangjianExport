package com.wang.mgt.service;

import com.wang.dataload.dto.ProformaInvoiceDTO;
import com.wang.mgt.dao.ProformaInvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProformaInvoiceService {

    @Autowired
    ProformaInvoiceDAO proformaInvoiceDAO;

    public ProformaInvoiceDTO searchProformaInvoiceByInvoiceNo(String proformaInvoiceNum){
        System.out.println("ProformaInvoiceService searchProformaInvoiceByInvoiceNo ");
        return proformaInvoiceDAO.searchProformaInvoiceByNum(proformaInvoiceNum);

    }
}
