package com.wang.dataload.data;

import com.wang.dataload.dto.Broker;
import com.wang.dataload.dto.ExportMerchant;
import com.wang.dataload.dto.FactoryPurchaseOrderDTO;
import com.wang.dataload.dto.FactoryPurchaseOrderItemDTO;
import com.wang.dataload.util.DataUtils;
import com.wang.dataload.util.ExporterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FactoryPurchaseOrderFormProcessor implements DataProcessor {

    private boolean remarkInd = false;

    private boolean orderFormItemInd = false;

    private boolean packingInd = false;

    private boolean deliveryInd = false;

    private boolean orderFormInd = false;

    private boolean exporterMerchantInd = false;

    private FactoryPurchaseOrderDTO factoryPurchaseOrderDTO;

    private FactoryPurchaseOrderItemDTO factoryPurchaseOrderItemDTO;

    private List<FactoryPurchaseOrderItemDTO> factoryPurchaseOrderItemDTOList;

    private ExportMerchant exportMerchant;

    private Broker broker;

    private StringBuffer remarkBF;

    private StringBuffer packageBF;

    public FactoryPurchaseOrderDTO getFactoryPurchaseOrderDTO() {
        return factoryPurchaseOrderDTO;
    }

    public void setFactoryPurchaseOrderDTO(FactoryPurchaseOrderDTO factoryPurchaseOrderDTO) {
        this.factoryPurchaseOrderDTO = factoryPurchaseOrderDTO;
    }

    public void processData(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor string cell value " + cellValueStr);
            if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_REMARK)) {
                remarkInd = true;
                orderFormItemInd = false;
                remarkBF = new StringBuffer();
            } else if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_ITEM_START)) {
                orderFormItemInd = true;
            } else if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_DELIVERY)) {
                deliveryInd = true;
                remarkInd = false;
                factoryPurchaseOrderDTO.setRemark(remarkBF.toString());
            } else if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_PACKAGE)) {
                packingInd = true;
                packageBF = new StringBuffer();
                deliveryInd = false;
            } else if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_NO)) {
                orderFormInd = true;
                if (factoryPurchaseOrderDTO == null) {
                    factoryPurchaseOrderDTO = new FactoryPurchaseOrderDTO();
                    broker = new Broker();
                    factoryPurchaseOrderDTO.setBroker(broker);
                    exportMerchant = new ExportMerchant();
                    factoryPurchaseOrderDTO.setExportMerchant(exportMerchant);
                    factoryPurchaseOrderItemDTOList = new ArrayList<FactoryPurchaseOrderItemDTO>();
                    factoryPurchaseOrderDTO.setFactoryPurchaseOrderItemDTOList(factoryPurchaseOrderItemDTOList);
                }
            } else if (cellValue.equals(ExporterConstants.FACTORY_ORDER_FORM_EXPORTER_NAME)) {
                orderFormInd = false;
                exporterMerchantInd = true;

            }
            if (orderFormInd) {
                processOrder(cell);
            } else if (exporterMerchantInd) {
                processExporterMerchant(cell);
            } else if (orderFormItemInd) {
                processOrderFormItem(cell);
            } else if (remarkInd) {
                processOrderFormRemark(cell);
            } else if (deliveryInd) {
                processOrderFormDelivery(cell);
            } else if (packingInd) {
                processOrderFormPacking(cell);
            }

        }
    }

    public void processOrder(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrder string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ORDER_COLUMN) {
                log.debug("setFactoryOrderNumber " + cellValueStr);
                factoryPurchaseOrderDTO.setFactoryOrderNumber(cellValueStr);
              }
            else if(cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_BROKER_NAME_COLUMN) {
                log.debug("setBrokerOverseasName " + cellValueStr);
                factoryPurchaseOrderDTO.getBroker().setBrokerOverseasName(cellValueStr);
            }
        }
    }

    public void processExporterMerchant(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processExporterMerchant string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_EXPORTER_MERCHANT_COLUMN) {
                log.debug("setMerchantName " + cellValueStr);
                factoryPurchaseOrderDTO.getExportMerchant().setMerchantName(cellValueStr);
            }
        }
    }


    public void processOrderFormDelivery(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrderFormDelivery string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_DELIVERY_COLUMN) {
                log.debug("setDeliverMode " + cellValueStr);
                factoryPurchaseOrderDTO.setDeliverMode(cellValueStr);
            }
        }
    }

    public void processOrderFormRemark(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrderFormRemark string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_REMARK_COLUMN) {
                log.debug("FACTORY_ORDER_FORM_REMARK_COLUMN " + cellValueStr);
                remarkBF.append(cellValueStr + "\n");

            }
        }
    }

    public void processOrderFormPacking(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        if (cellValue instanceof String) {
            String cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrderFormPacking string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_PACKAGE_COLUMN) {
                log.debug("FACTORY_ORDER_FORM_PACKAGE_COLUMN " + cellValueStr);
                packageBF.append(cellValueStr + "\n");

            }
        }
    }

    public void processOrderFormItem(Cell cell) {
        Object cellValue = DataUtils.getCellValue(cell);
        Double cellValueDouble = null;
        String cellValueStr = null;
        if (cellValue instanceof String) {
            cellValueStr = (String) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrderFormItem string cell value " + cellValueStr);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ITEM_EXPORTER_MODELS_COLUMN) {
                if (!cellValueStr.equals(ExporterConstants.FACTORY_ORDER_FORM_ITEM_MODEL)) {
                    log.debug("FACTORY_ORDER_FORM_ITEM_MODEL    " + cellValueStr);
                    if (factoryPurchaseOrderItemDTO != null) {

                        factoryPurchaseOrderDTO.getFactoryPurchaseOrderItemDTOList().add(factoryPurchaseOrderItemDTO);
                        log.debug("getFactoryPurchaseOrderItemDTOList " + factoryPurchaseOrderDTO.getFactoryPurchaseOrderItemDTOList().toString());
                    }
                    factoryPurchaseOrderItemDTO = new FactoryPurchaseOrderItemDTO();
                    factoryPurchaseOrderItemDTO.setProductModel(cellValueStr);
                }
            } else if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ITEM_EXPORTER_REMARK_COLUMN) {
                if (!cellValueStr.equals(ExporterConstants.FACTORY_ORDER_FORM_ITEM_REMARK)) {
                    log.debug("FACTORY_ORDER_FORM_ITEM_REMARK   " + cellValueStr);

                    factoryPurchaseOrderItemDTO.setRemark(cellValueStr);
                }
            }
        } else if (cellValue instanceof Double) {
            cellValueDouble = (Double) cellValue;
            log.debug("FactoryPurchaseOrderFormProcessor processOrderFormItem double cell value " + cellValueDouble);
            if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ITEM_EXPORTER_QUANTITY_COLUMN) {

                log.debug("quantity   " + cellValueDouble);
                Integer quantity = (int) cellValueDouble.doubleValue();
                factoryPurchaseOrderItemDTO.setQuantity(quantity);
            } else if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ITEM_UNIT_PRICE_COLUMN) {
                log.debug("unit price   " + cellValueDouble);
                BigDecimal unitPrice = BigDecimal.valueOf(cellValueDouble.doubleValue());
                factoryPurchaseOrderItemDTO.setUnitPriceRMB(unitPrice);
            } else if (cell.getColumnIndex() == ExporterConstants.FACTORY_ORDER_FORM_ITEM_TOTAL_COLUMN) {
                BigDecimal amount = BigDecimal.valueOf(cellValueDouble.doubleValue());
                log.debug("AMOUNT   " + amount);
                factoryPurchaseOrderItemDTO.setAmountRMB(amount);
            }
        }
    }


}