package com.wang.dataload.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImportMerchant {

    private String merchantName;

    private String merchantAddress;

    private Date createTime;

    private Date lastUpdateDate;

}
