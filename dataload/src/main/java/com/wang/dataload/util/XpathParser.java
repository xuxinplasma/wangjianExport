package com.wang.dataload.util;

import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import java.io.StringReader;
import org.xml.sax.InputSource;

public class XpathParser {

    public static void main(String[] args) {
        // 1. 需要解析的 XML 字符串
        String xmlString = "<root>" +
                "<person>" +
                "<name>John</name>" +
                "<age>30</age>" +
                "</person>" +
                "<person>" +
                "<name>Jane</name>" +
                "<age>25</age>" +
                "</person>" +
                "</root>";

        try {
            // 2. 将 XML 字符串解析为 Document 对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xmlString));
            Document document = builder.parse(inputSource);

            // 3. 创建 XPathFactory 和 XPath 对象
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            // 4. 使用 XPath 表达式查找所有 <person> 节点
            String expression = "/root/person";
            NodeList nodeList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

            // 5. 遍历 NodeList，获取每个 <person> 节点的内容
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node personNode = nodeList.item(i);

                // 获取 <name> 和 <age> 的值
                String name = xpath.evaluate("name", personNode);
                String age = xpath.evaluate("age", personNode);

                System.out.println("Person Name: " + name);
                System.out.println("Person Age: " + age);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
