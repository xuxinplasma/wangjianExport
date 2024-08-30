package com.wang.mgt.service;

import com.wang.dataload.dto.ImportMerchant;
import com.wang.mgt.dao.MerchantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    public MerchantDAO merchantDAO;

    public ImportMerchant ListImporterMerchant(Integer merchantId){
        ImportMerchant importMerchant = merchantDAO.listByMerchantId(merchantId);
        return  importMerchant;
    }
}
