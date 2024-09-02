package com.wang.dataload.service;

import com.wang.dataload.dao.ProformaInvoiceMapper;
import com.wang.dataload.dto.Broker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProformaInvoiceTest {

    @Autowired
    private ProformaInvoiceMapper proformaInvoiceMapper;


    @Test
    public void InsertBroker(){
        log.debug("before save broker");
        Broker broker = new Broker();
        broker.setBankAccount("12345");
        broker.setBankName("bnp");
        broker.setBankAddress("raffle");
        broker.setBrokerOverseasName("canada merchant1");
        broker.setBrokerOverseasAddress("toronto");
        broker.setBrokerDomesticName("merchant1");
        broker.setBrokerDomesticAddress("hangzhou");
        broker.setSwiftCode("abccd");
        broker.setCreateTime(new Date());
        proformaInvoiceMapper.insertBroker(broker);
        log.debug("aftere save broker");
    }
}
