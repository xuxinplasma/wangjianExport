package com.wang.dataload.service;

import com.wang.dataload.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wang.dataload.dao.ProformaInvoiceMapper;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PersistentOrderService {

    @Autowired
    public ProformaInvoiceMapper proformaInvoiceMapper;

    //   @Transactional
//    public void createOrder(Order order, List<OrderItem> orderItems) {
//        // 插入订单记录，MyBatis会自动将生成的ID设置到order对象中
//        orderMapper.insertOrder(order);
//
//        // 获取生成的订单ID
//        Long orderId = order.getId();
//
//        // 插入每个订单项，使用生成的订单ID
//        for (OrderItem item : orderItems) {
//            item.setOrderId(orderId);
//            orderItemMapper.insertOrderItem(item);
//        }
//    }

    @Transactional
    public void persistentOrder(ProformaInvoiceDTO proformaInvoiceDTO, List<FactoryPurchaseOrderDTO> factoryPurchaseOrderDTOList) {
        saveProformaInvoice(proformaInvoiceDTO);
        saveFactoryPurchaseOrder(factoryPurchaseOrderDTOList, proformaInvoiceDTO.getId());
    }

    @Transactional
    public void saveProformaInvoice(ProformaInvoiceDTO proformaInvoiceDTO) {
        log.debug("proformaInvoiceDTO " + proformaInvoiceDTO.toString());
        proformaInvoiceMapper.insertBroker(proformaInvoiceDTO.getBroker());
        log.debug("after save broker " + proformaInvoiceDTO.getBroker().getId());
        proformaInvoiceMapper.insertImportMerchant(proformaInvoiceDTO.getImportMerchant());
        log.debug("after save importMerchant " + proformaInvoiceDTO.getImportMerchant().getId());
        proformaInvoiceMapper.insertProformaInvoice(proformaInvoiceDTO);
        log.debug("after save proformaInvoiceDTO  " + proformaInvoiceDTO.getId());

        List<ProformaInvoiceOrderItemDTO> proformaInvoiceOrderItemDTOList = proformaInvoiceDTO.getProformaInvoiceOrderItemDTOList();
        for (ProformaInvoiceOrderItemDTO proformaInvoiceOrderItemDTO : proformaInvoiceOrderItemDTOList) {
            log.debug("proformaInvoiceOrderItemDTO " + proformaInvoiceOrderItemDTO.toString());
            proformaInvoiceMapper.insertProduct(proformaInvoiceOrderItemDTO.getProduct());
            proformaInvoiceOrderItemDTO.setProformaInvoiceOrderId(proformaInvoiceDTO.getId());
            proformaInvoiceMapper.insertProformaInvoiceItem(proformaInvoiceOrderItemDTO);
            log.debug("after save proformaInvoiceOrderItem  " + proformaInvoiceOrderItemDTO.getId());
        }

    }


    @Transactional
    public void saveFactoryPurchaseOrder(List<FactoryPurchaseOrderDTO> factoryPurchaseOrderDTOList, Integer proformaInvoiceId) {

        for (FactoryPurchaseOrderDTO factoryPurchaseOrderDTO : factoryPurchaseOrderDTOList) {
            log.debug("factoryPurchaseOrderDTO " + factoryPurchaseOrderDTO.toString());
            factoryPurchaseOrderDTO.setProformaInvoiceId(proformaInvoiceId);
            proformaInvoiceMapper.insertBroker(factoryPurchaseOrderDTO.getBroker());
            log.debug("after save broker " + factoryPurchaseOrderDTO.getBroker().getId());
            proformaInvoiceMapper.insertExportMerchant(factoryPurchaseOrderDTO.getExportMerchant());
            log.debug("after save exportMerchant " + factoryPurchaseOrderDTO.getExportMerchant().getId());
            proformaInvoiceMapper.insertFactoryPurchaseOrder(factoryPurchaseOrderDTO);
            log.debug("after save factoryPurchaseOrder " + factoryPurchaseOrderDTO.getId());
            List<FactoryPurchaseOrderItemDTO> factoryPurchaseOrderItemDTOList = factoryPurchaseOrderDTO.getFactoryPurchaseOrderItemDTOList();
            for (FactoryPurchaseOrderItemDTO factoryPurchaseOrderItemDTO : factoryPurchaseOrderItemDTOList) {
                log.debug("factoryPurchaseOrderItemDTO " + factoryPurchaseOrderItemDTO.toString());
                factoryPurchaseOrderItemDTO.setFactoryPurchaseOrderId(factoryPurchaseOrderDTO.getId());
                proformaInvoiceMapper.insertFactoryPurchaseOrderItem(factoryPurchaseOrderItemDTO);
                log.debug("after save factoryPurchaseOrderItemDTO " + factoryPurchaseOrderItemDTO.getId());
            }

        }

    }

}
