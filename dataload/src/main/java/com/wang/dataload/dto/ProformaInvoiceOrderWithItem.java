package com.wang.dataload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProformaInvoiceOrderWithItem {

    private Integer id;

    private String proformaInvoiceNum;

    private String shippingMethod;

    private String paymentMethod;

    private Date proformaInvoiceDate;

    private String productModel;

    private BigDecimal unitPriceRMB;

    private BigDecimal unitPriceUSD;

}
