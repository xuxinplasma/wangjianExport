package com.wang.dataload.util;

import javax.print.DocFlavor;

public interface ExporterConstants {

    String filePath = "F:\\xxhncs\\xxh\\wangjian\\22W-012 QG.xlsx";
    //Proforma Invoice
    String PROFORMA_INVOICE = "PROFORMA INVOICE";
    String FACTORY_PURCHASE_ORDER = "出 口 产 品 采 购 合 同";
    String FACTORY_PURCHASE_ORDER_FORM = "订      单";
    String PROFORMA_INVOICE_IMPORTER_TO = "TO:";
    String PROFORMA_INVOICE_BROKER_FROM = "FROM:";
    int  PROFORMA_INVOICE_IMPORTER_TO_COLUMN = 1;
    int  PROFORMA_INVOICE_BROKER_FROM_COLUMN = 1;
    int  PROFORMA_INVOICE_MARKS_COLUMN = 3;
    int  PROFORMA_INVOICE_BROKER_BANK_COLUMN = 1;
    String PROFORMA_INVOICE_NO = "INVOICE NO";
    String PROFORMA_INVOICE_DATE = "DATED";
    String PROFORMA_INVOICE_SHIPMENT = "SHIPMENT";
    String PROFORMA_INVOICE_TERM = "TERM";
    String PROFORMA_INVOICE_FROM = "FROM:";
    String PROFORMA_INVOICE_TO = "TO:";
    String PROFORMA_INVOICE_REMARK = "MARKS:";

    String BROKER_BANK_NAME = "Bank name";
    String BANK_ADDRESS = "Bank address";
    String SWIFT_CODE= "SWIFT Code";
    String COMPANY = "Company";
    String ACCOUNT_NUMBER = "Account Number";
    String COLON_DELIMITOR = ":";
    String CHINESE_COLON_DELIMITOR = "：";
    String BLANK = "BLANK";
    String TOTALS =  "TOTAL:";
    String SAY = "SAY:";
    String DELIVERY = "Delivery:";

    String BANK_INFORMATION = "Bank information:";

    //Proforma Invoice Order Items
    String PROFORMA_INVOICE_ORDER_ITEM_NOS = "Nos.";
    String DESCRIPTION_OF_GOODS = "Description of goods";
    String QUANTITY = "Quantity";

    String UNIT_PRICE = "Unit price";
    String AMOUNT = "Amount";
    String PCS = "PCS";
    String CIF_VANCOUVER_IN_USD = "CIF VANCOUVER IN USD";
    int DESCRIPTION_OF_GOODS_COLUMN = 1;
    int QUANTITY_COLUMN = 2;
    int UNIT_PRICE_COLUMN = 3;
    int AMOUNT_COLUMN = 4;


    //factory order
    String FACTORY_ORDER_REMARK = "买卖双方同意订立合同条款如下：";
    String FACTORY_ORDER_REMARK_2 = "产品上激光打字， 客户型号，订单日期";
    String FACTORY_ORDER_SELL_STAMP = "卖方（盖章）";
    String FACTORY_ORDER_ITEM_IMPORTER_MODELS = "客户型号";
    String FACTORY_ORDER_ITEM_EXPORTER_MODELS = "备注（工厂型号）";
    String FACTORY_ORDER_ITEM_QUANTITY = "数量（个）";
    String FACTORY_ORDER_ITEM_UNIT_PRICE = "单价（元）";
    String FACTORY_ORDER_ITEM_TOTAL = "合计（元）";
    String FACTORY_ORDER_NO = "合同号";
    String FACTORY_ORDER_DATE = "签约日期";
    String FACTORY_ORDER_SIGN_LOCATION = "签约地";
    String FACTORY_ORDER_BROKER_NAME = "买方";
    String FACTORY_ORDER_BROKER_ADDRESS = "地址";
    String FACTORY_ORDER_BROKER_PHONE = "电话";
    String FACTORY_ORDER_EXPORTER_NAME = "卖方";
    String FACTORY_ORDER_EXPORTER_ADDRESS = "地址";
    String FACTORY_ORDER_EXPORTER_PHONE = "电话";

    int FACTORY_ORDER_ITEM_IMPORTER_MODELS_COLUMN = 0;

    int FACTORY_ORDER_ITEM_EXPORTER_MODELS_COLUMN = 1;
    int FACTORY_ORDER_ITEM_EXPORTER_QUANTITY_COLUMN = 2;
    int FACTORY_ORDER_ITEM_UNIT_PRICE_COLUMN = 3;
    int FACTORY_ORDER_ITEM_TOTAL_COLUMN = 4;
    int FACTORY_ORDER_BROKER_COLUMN = 0;
    int FACTORY_ORDER_EXPORTER_COLUMN = 2;

    //factory_order_form
    String FACTORY_ORDER_FORM = "订      单";
    String FACTORY_ORDER_FORM_NO = "订单号：";
    String FACTORY_ORDER_FORM_EXPORTER_NAME = "生产厂家：";
    String FACTORY_ORDER_FORM_ITEM_START = "以下产品请安排生产：";
    String FACTORY_ORDER_FORM_PACKAGE = "产品包装：";
    String FACTORY_ORDER_FORM_DELIVERY = "交货：";
    String FACTORY_ORDER_FORM_REMARK = "要求：";
    String FACTORY_ORDER_FORM_ITEM_REMARK = "备注";
    String FACTORY_ORDER_FORM_ITEM_MODEL = "客户型号";
    String FACTORY_ORDER_FORM_ITEM_SUMMARY = "总计";
    String FACTORY_ORDER_FORM_ITEM_QUANTITY = "数量(个）";
    String FACTORY_ORDER_FORM_ITEM_UNIT_PRICE= "单价(元)";
    String FACTORY_ORDER_FORM_ITEM_TOTAL = "合计(元)";
    String WANGJIAN_EOD = "汪健 2022-4-26";


    int FACTORY_ORDER_FORM_REMARK_COLUMN = 1;
    int FACTORY_ORDER_FORM_EXPORTER_MERCHANT_COLUMN = 1;
    int FACTORY_ORDER_FORM_ORDER_COLUMN = 1;
    int FACTORY_ORDER_FORM_BROKER_NAME_COLUMN = 4;
    int FACTORY_ORDER_FORM_DELIVERY_COLUMN = 1;
    int FACTORY_ORDER_FORM_PACKAGE_COLUMN = 1;
    int FACTORY_ORDER_FORM_ITEM_EXPORTER_MODELS_COLUMN = 0;
    int FACTORY_ORDER_FORM_ITEM_EXPORTER_REMARK_COLUMN = 1;
    int FACTORY_ORDER_FORM_ITEM_EXPORTER_QUANTITY_COLUMN = 2;
    int FACTORY_ORDER_FORM_ITEM_UNIT_PRICE_COLUMN = 3;
    int FACTORY_ORDER_FORM_ITEM_TOTAL_COLUMN = 4;

    String  PROFORMAL_INVOICE_SHEET = "Sheet1";
    String FACTORY_PURCHASE_ORDER_YOUKAI = "优凯";
    String FACTORY_PURCHASE_ORDER_SIJIEER = "斯捷尔";
    String FACTORY_PURCHASE_ORDER_ZHANWANG = "展望";

    //Product
    String CENTER_BEARING_FROM = "CENTER BEARING FROM";
    String CENTER_BEARING_FROM_CN = "中心支架";
    String PURCHASE_ORDER = "Purchase Order";
    String BOOT_KIT_FROM = "BOOT KIT FROM";
    String BOOT_KIT_FROM_CN = "防尘罩附件包";
}
