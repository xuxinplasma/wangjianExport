package com.wang.dataload.service;

import com.wang.dataload.dto.ProformaInvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wang.dataload.dao.ProformaInvoiceMapper;

@Service
public class ProformaInvoiceService {

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

    public void saveProformaInvoice(ProformaInvoiceDTO proformaInvoiceDTO) {
        proformaInvoiceMapper.insertBroker(proformaInvoiceDTO.getBroker());
    }

}
