package com.wang.mgt.dao;

import com.wang.dataload.dto.ProformaInvoiceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProformaInvoiceDAO {

    ProformaInvoiceDTO searchProformaInvoiceByNum(@Param("proformaInvoiceNum") String proformaInvoiceNum );
}
