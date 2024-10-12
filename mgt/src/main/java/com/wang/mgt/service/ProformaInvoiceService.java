package com.wang.mgt.service;

import com.wang.dataload.dto.ProformaInvoiceDTO;
import com.wang.dataload.dto.ProformaInvoiceOrderWithItem;
import com.wang.mgt.dao.ProformaInvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProformaInvoiceService {

    @Autowired
    ProformaInvoiceDAO proformaInvoiceDAO;

    public ProformaInvoiceDTO searchProformaInvoiceByInvoiceNo(String proformaInvoiceNum){
        System.out.println("ProformaInvoiceService searchProformaInvoiceByInvoiceNo ");
        return proformaInvoiceDAO.searchProformaInvoiceByNum(proformaInvoiceNum);

    }

    public List<ProformaInvoiceOrderWithItem> selectProformaInvoiceWithItems(String productModel){
        System.out.println("ProformaInvoiceService selectProformaInvoiceWithItems ");
        return proformaInvoiceDAO.selectProformaInvoiceWithItems(productModel);

    }
}
