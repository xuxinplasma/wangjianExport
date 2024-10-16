package com.wang.mongodataload;

import com.wang.dataload.data.DataFileImporter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

/**
 * 启动类
 *
 * @author admin
 */
@SpringBootApplication
@MapperScan("com.wang.dataload.dao")
public class Application {


    DataFileImporter dataFileImporter;

    public static void main(String[] args) {

        ApplicationContext context  = SpringApplication.run(Application.class, args);
        DataFileImporter dataFileImporter = context.getBean(DataFileImporter.class);
        dataFileImporter.fileImporter(args);
    }


}
