package com.wang.mongodataload.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "proformaInvoice" )
public class ProformaInvoiceDTO {

    @Id
    private Integer id;

    private String proformaInvoiceNum;

    private String shippingMethod;

    private String paymentMethod;

    private String proformaInvoiceTo;

    private String proformaInvoiceFrom;

    private ImportMerchant importMerchant;

    private List<ProformaInvoiceOrderItemDTO> proformaInvoiceOrderItemDTOList;

    private Broker broker;

    private String remark;

    private Date proformaInvoiceDate;

    private Date createTime;

    private Date lastUpdateDate;


}
