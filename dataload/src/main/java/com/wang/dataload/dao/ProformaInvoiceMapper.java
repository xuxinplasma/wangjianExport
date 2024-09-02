package com.wang.dataload.dao;

import com.wang.dataload.dto.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ProformaInvoiceMapper {

       public void insertBroker(Broker broker);

        public void insertImportMerchant(ImportMerchant importMerchant);

        public  void insertExportMerchant(ExportMerchant exportMerchant);

        public void insertProformaInvoice(ProformaInvoiceDTO proformaInvoiceDTO);

        public void insertProformaInvoiceItem(ProformaInvoiceOrderItemDTO proformaInvoiceOrderItemDTO);

        public void insertFactoryPurchaseOrder(FactoryPurchaseOrderDTO factoryPurchaseOrderDTO);

        public void insertFactoryPurchaseOrderItem(FactoryPurchaseOrderItemDTO factoryPurchaseOrderItemDTO);


}
