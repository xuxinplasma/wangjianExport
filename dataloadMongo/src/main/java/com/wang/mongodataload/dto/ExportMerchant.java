package com.wang.mongodataload.dto;

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
public class ExportMerchant {

    private Integer id;

    private String merchantName;

    private String merchantAddress;

    private String merchantPhone;

    private Date createTime;

    private Date lastUpdateDate;

}
