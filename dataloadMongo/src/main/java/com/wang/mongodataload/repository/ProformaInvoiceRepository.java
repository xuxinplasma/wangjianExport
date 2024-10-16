package com.wang.mongodataload.repository;

import com.wang.mongodataload.dto.ProformaInvoiceDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProformaInvoiceRepository extends MongoRepository<ProformaInvoiceDTO,Integer> {
}
