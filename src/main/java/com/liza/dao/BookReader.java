package com.liza.dao;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BookReader {

    public XSSFWorkbook read(String filename) {
        XSSFWorkbook book = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(filename));
            book = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        return book;
    }
}
