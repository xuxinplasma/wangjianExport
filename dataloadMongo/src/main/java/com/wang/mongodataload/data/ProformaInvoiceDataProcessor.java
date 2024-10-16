package com.wang.mongodataload.data;

import com.wang.mongodataload.dto.*;
import com.wang.mongodataload.util.DataUtils;
import com.wang.mongodataload.util.ExporterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
public class ProformaInvoiceDataProcessor implements DataProcessor {

    private boolean importerAddressInd = false;

    private boolean brokerAddressInd = false;

    private boolean orderItemInd = false;

    private boolean brokerBankInd = false;

    private ProformaInvoiceDTO proformaInvoiceDTO;

    private ProformaInvoiceOrderItemDTO proformaInvoiceOrderItemDTO;

    private List<ProformaInvoiceOrderItemDTO> proformaInvoiceOrderItemDTOList;

    private ImportMerchant importMerchant;

    private Broker broker;

    private Product product;

    private StringBuffer importerAddressSB;

    private StringBuffer brokerAddressSB;

    private StringBuffer marksSB;

    public void processData(Cell cell) {

        //处理proforma invoice 的 外贸商地址

        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("PerfInvoiceDataProcessor string cell value " + cellValueStr);
            if (cellValue.equals(ExporterConstants.PROFORMA_INVOICE_IMPORTER_TO)) {
                importerAddressInd = true;
                importerAddressSB = new StringBuffer("");
                proformaInvoiceDTO = new ProformaInvoiceDTO();
                proformaInvoiceDTO.setCreateTime(new Date());
                importMerchant = new ImportMerchant();
                importMerchant.setCreateTime(new Date());
                broker = new Broker();
                broker.setCreateTime(new Date());
                proformaInvoiceOrderItemDTOList = new ArrayList<ProformaInvoiceOrderItemDTO>();
                proformaInvoiceDTO.setBroker(broker);
                proformaInvoiceDTO.setImportMerchant(importMerchant);
                proformaInvoiceDTO.setProformaInvoiceOrderItemDTOList(proformaInvoiceOrderItemDTOList);
            } else if (cellValue.equals(ExporterConstants.PROFORMA_INVOICE_BROKER_FROM)) {
                importerAddressInd = false;
                proformaInvoiceDTO.getImportMerchant().setMerchantAddress(importerAddressSB.toString());
                brokerAddressInd = true;
                brokerAddressSB = new StringBuffer("");
            } else if (cellValue.equals(ExporterConstants.PROFORMA_INVOICE_ORDER_ITEM_NOS)) {
                brokerAddressInd = false;
                proformaInvoiceDTO.getBroker().setBrokerOverseasAddress(brokerAddressSB.toString());
                orderItemInd = true;
                proformaInvoiceDTO.setRemark(marksSB.toString());

            }else if (cellValue.equals(ExporterConstants.BANK_INFORMATION)) {
                orderItemInd = false;

                brokerBankInd = true;
           }
       }
        log.debug("orderItemInd " + orderItemInd);
        if (importerAddressInd) {
            processImporterAddress(cell);
        } else if (brokerAddressInd) {
            log.debug("PerfInvoiceDataProcessor call processBrokerAddress ");
            processBrokerAddress(cell);
        } else if (orderItemInd) {
            processOrderItem(cell);
        } else if(brokerBankInd){
            processBrokerBank(cell);
        }
        if(proformaInvoiceDTO != null)
          log.debug("proformaInvoiceDTO " + proformaInvoiceDTO.toString());

    }

    public void processImporterAddress(Cell cell) {
        log.debug("Importer address column " + cell.getColumnIndex());
        Object cellValue = DataUtils.getCellValue(cell);
        String cellValueStr = null;
        try {
            if (cellValue instanceof String) {
                cellValueStr = (String) cellValue;
                log.debug("PerfInvoiceDataProcessor importAddress  " + cellValueStr);
                if (cell.getColumnIndex() == ExporterConstants.PROFORMA_INVOICE_IMPORTER_TO_COLUMN) {
                    importerAddressSB.append(cellValueStr + "\n");

                } else if (cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_NO) && cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_DATE)) {
                    int dateIndex = cellValueStr.indexOf(ExporterConstants.PROFORMA_INVOICE_DATE);
                    String invoiceNo = cellValueStr.substring(0, dateIndex);
                    String invoiceDate = cellValueStr.substring(dateIndex, cellValueStr.length());
                    String[] invoiceNoParts = invoiceNo.split(":");
                    String[] invoiceDateParts = invoiceDate.split(":");
                    proformaInvoiceDTO.setProformaInvoiceNum(invoiceNoParts[1].trim());
                    log.debug("invoiceNo " + invoiceNoParts[1].trim());
                    log.debug("invoiceDate " + invoiceDateParts[1].trim());
                    SimpleDateFormat formatter1 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                    Date localDate = formatter1.parse(invoiceDateParts[1].trim().toLowerCase());

                    proformaInvoiceDTO.setProformaInvoiceDate(localDate);

                } else if (cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_SHIPMENT) && cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_TERM)) {
                    int termIndex = cellValueStr.indexOf(ExporterConstants.PROFORMA_INVOICE_TERM);
                    String shipmentMode = cellValueStr.substring(0, termIndex);
                    String termMode = cellValueStr.substring(termIndex, cellValueStr.length());
                    String[] shipmentModeParts = shipmentMode.split(":");
                    String[] termModeParts = termMode.split(":");
                    proformaInvoiceDTO.setShippingMethod(shipmentModeParts[1].trim());

                    proformaInvoiceDTO.setPaymentMethod(termModeParts[1].trim());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //处理Proforma订单的内容

    }

    public void processBrokerAddress(Cell cell) {
        log.debug("broker address column " + cell.getColumnIndex());
        Object cellValue = DataUtils.getCellValue(cell);
        String cellValueStr = null;
        if (cellValue instanceof String) {
            cellValueStr = (String) cellValue;
            log.debug("PerfInvoiceDataProcessor brokerAddress  " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.PROFORMA_INVOICE_BROKER_FROM_COLUMN) {

                brokerAddressSB.append(cellValueStr + "\n") ;
            } else if (cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_FROM) && cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_TO)) {
                int toIndex = cellValueStr.indexOf(ExporterConstants.PROFORMA_INVOICE_TO);
                String proformaInvoiceFrom = cellValueStr.substring(0, toIndex);
                String proformaInvoiceTo = cellValueStr.substring(toIndex, cellValueStr.length());
                log.debug("PerfInvoiceDataProcessor brokerAddress proformaInvoiceFrom  " + proformaInvoiceFrom);
                log.debug("PerfInvoiceDataProcessor brokerAddress proformaInvoiceTo  " + proformaInvoiceTo);
                String[] proformamaInvoiceFromParts = proformaInvoiceFrom.split(":");
                String[] proformamaInvoiceToParts = proformaInvoiceTo.split(":");
                proformaInvoiceDTO.setProformaInvoiceTo(proformamaInvoiceToParts[1].trim());
                proformaInvoiceDTO.setProformaInvoiceFrom(proformamaInvoiceFromParts[1].trim());

            } else if (cellValueStr.contains(ExporterConstants.PROFORMA_INVOICE_REMARK)) {
                log.debug("PerfInvoiceDataProcessor brokerAddress create marksSB  ");
                marksSB = new StringBuffer("");

            } else if (cell.getColumnIndex() == ExporterConstants.PROFORMA_INVOICE_MARKS_COLUMN) {
                log.debug("PerfInvoiceDataProcessor brokerAddress append marksSB  ");
                marksSB.append(cellValueStr + "\n");
            }
        }
   }

    public void processOrderItem(Cell cell) {
        log.debug("order itemcolumn " + cell.getColumnIndex());
        Object cellValue = DataUtils.getCellValue(cell);
        String cellValueStr = null;
        Double cellValueDouble = null;
        if (cellValue instanceof String) {
            cellValueStr = (String) cellValue;
            log.debug("PerfInvoiceDataProcessor order item   " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.DESCRIPTION_OF_GOODS_COLUMN) {
                if (!cellValueStr.equals(ExporterConstants.DESCRIPTION_OF_GOODS)) {
                    log.debug("DESCRIPTION_OF_GOODS    " + cellValueStr);
                    if(proformaInvoiceOrderItemDTO != null) {

                        proformaInvoiceDTO.getProformaInvoiceOrderItemDTOList().add(proformaInvoiceOrderItemDTO);
                        log.debug("getProformaInvoiceOrderItemDTOList " + proformaInvoiceDTO.getProformaInvoiceOrderItemDTOList().toString());
                    }
                    proformaInvoiceOrderItemDTO = new ProformaInvoiceOrderItemDTO();
                    proformaInvoiceOrderItemDTO.setCreateTime(new Date());
                    product = new Product();
                    if(cellValueStr.contains("/")){
                        String[] productModelArray = cellValueStr.split("/");
                        proformaInvoiceDTO.getBroker().setBankName(brokerBankName[1].trim());
                        proformaInvoiceOrderItemDTO.setProductModel(cellValueStr);
                    }
                    else {

                        proformaInvoiceDTO.getBroker().setBankName(brokerBankName[1].trim());
                        product.setImportProductModel(cellValueStr);
                        proformaInvoiceOrderItemDTO.setProduct(product);
                    }
                }
            } else if (cell.getColumnIndex() == ExporterConstants.UNIT_PRICE_COLUMN) {
                if (cellValueStr.equals(ExporterConstants.CIF_VANCOUVER_IN_USD)) {
                    proformaInvoiceDTO.setRemark(proformaInvoiceDTO.getRemark() + "\n" + cellValueStr);

                }
            } else if (cell.getColumnIndex() == ExporterConstants.AMOUNT_COLUMN) {
                log.debug("AMOUNT   " + cellValueStr);
                if (!cellValueStr.equals(ExporterConstants.AMOUNT)) {
                    long quantity = proformaInvoiceOrderItemDTO.getQuantity().longValue();
                    BigDecimal amount = proformaInvoiceOrderItemDTO.getUnitPriceUSD().multiply(BigDecimal.valueOf(quantity));
                    proformaInvoiceOrderItemDTO.setAmountUSD(amount);
             }
         }
        } else if (cellValue instanceof Double) {
            cellValueDouble = (Double) cellValue;
           if (cell.getColumnIndex() == ExporterConstants.QUANTITY_COLUMN) {
                log.debug("quantity   " + cellValueDouble);
                Integer quantity = (int) cellValueDouble.doubleValue();
                proformaInvoiceOrderItemDTO.setQuantity(quantity);
          }
            else if (cell.getColumnIndex() == ExporterConstants.UNIT_PRICE_COLUMN) {
                log.debug("unit price   " + cellValueDouble);
                BigDecimal unitPrice = BigDecimal.valueOf(cellValueDouble.doubleValue());
                proformaInvoiceOrderItemDTO.setUnitPriceUSD(unitPrice);
         }
            else if (cell.getColumnIndex() == ExporterConstants.AMOUNT_COLUMN) {
             BigDecimal amount = BigDecimal.valueOf(cellValueDouble.doubleValue());
                log.debug("AMOUNT   " + amount);
                    proformaInvoiceOrderItemDTO.setAmountUSD(amount);
             }
        }
        if(proformaInvoiceOrderItemDTO != null) {
            log.debug("processOrderItem proformaInvoiceOrderItemDTO " + proformaInvoiceOrderItemDTO.toString());
        }
   }

    public void processBrokerBank(Cell cell) {
        log.debug("broker bank column " + cell.getColumnIndex());
        Object cellValue = DataUtils.getCellValue(cell);
        String cellValueStr = null;
        if (cellValue instanceof String) {
            cellValueStr = (String) cellValue;
            log.debug("PerfInvoiceDataProcessor brokerBank  " + cellValueStr);
            if (cellValueStr.contains(ExporterConstants.BROKER_BANK_NAME) ) {
           String[] brokerBankName = cellValueStr.split(":");
               proformaInvoiceDTO.getBroker().setBankName(brokerBankName[1].trim());

            } else if (cellValueStr.contains(ExporterConstants.BANK_ADDRESS) ) {
          String[] brokerBankAddress = cellValueStr.split(":");
                proformaInvoiceDTO.getBroker().setBankAddress(brokerBankAddress[1].trim());
            }
            else if (cellValueStr.contains(ExporterConstants.SWIFT_CODE) ) {
                String[] brokerSwiftCode = cellValueStr.split(":");
                proformaInvoiceDTO.getBroker().setSwiftCode(brokerSwiftCode[1].trim());
            }
            else if (cellValueStr.contains(ExporterConstants.COMPANY) ) {
                String[] brokerCompany = cellValueStr.split(":");
                proformaInvoiceDTO.getBroker().setBrokerOverseasName(brokerCompany[1].trim());
            }
            else if (cellValueStr.contains(ExporterConstants.ACCOUNT_NUMBER) ) {
                String[] brokerAccountNumber = cellValueStr.split(":");
                proformaInvoiceDTO.getBroker().setBankAccount(brokerAccountNumber[1].trim());
            }

        }
    }

    public ProformaInvoiceDTO getProformaInvoiceDTO() {
        return proformaInvoiceDTO;
    }

    public void setProformaInvoiceDTO(ProformaInvoiceDTO proformaInvoiceDTO) {
        this.proformaInvoiceDTO = proformaInvoiceDTO;
    }
}
