package com.liza.dao;

import com.liza.model.Well;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoCreatorTest {
    private static final String filename = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\08\\AutoReport-20180801.xlsx";
    private static final String directoryName = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\08";
    private static final String sheetName = "Скважины МЛСП";
    private static String type = "";
    private static String name = "";
    private static List<Well> list = new ArrayList<>();

    public enum WellNames{
        P11("СкважинаР-11","withZones","11"),
        P12("СкважинаР-12","gaugesTP","12"),
        P13("Скважина13","withZones","13"),
        P14("СкважинаР-14","gaugesPT","14"),
        P15("Скважина15","gaugesPT","15"),
        P16("Скважина16","gaugesTP","16"),
        P101("Скважина101","gaugesTP","101"),
        P102("Скважина102","gaugesTP","102"),
        P103("Скважина103","gaugesTP","103"),
        P104("Скважина104","gaugesPT","104"),
        P105("Скважина105","gaugesTP","105"),
        P106("Скважина106","gaugesTP","106"),
        P107("Скважина107","gaugesTP","107"),
        P108("Скважина108","gaugesTP","108"),
        P109("Скважина109","gaugesTP","109"),
        P110("Скважина110","gaugesTP","110"),
        P113("Скважина113","gaugesTP","113"),
        P114("Скважина114","gaugesTP","114"),
        P115("Скважина115","gaugesTP","115"),
        P116("Скважина116","gaugesTP","116"),
        P117("Скважина117","gaugesPT","117"),
        P120("Скважина120","gaugesTP","120"),
        P121("Скважина121","gaugesTP","121"),
        P122("Скважина122","gaugesTP","122"),
        G1("СкважинаG-1","gaugesTP","G01"),
        G1BIS("СкважинаG-1БИС","gaugesTP","G01bis"),
        VP1("УстановкаподготовкипластовойводыВП-1","VP","VP_1"),
        VP2("СкважинаВП-2","VP2","VP_2"),
        VP3("УстановкаподготовкипластовойводыВП-3","VP","VP_3");

        private final String name;
        private final String type;
        private final String code;
        WellNames(String name, String type, String code) { this.name = name; this.type = type; this.code = code; }
        public String getName() { return name;}
        public String getType() { return type;}
        public String getCode() {return  code;}
    }

    @Test
    void createEntitiesFromBook() {
        XSSFWorkbook book = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(filename));
            book = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        XSSFSheet sheet = book.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Well well = new Well();
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        String str = cell.getStringCellValue().replaceAll(" ", "");
                        boolean starts = (str.startsWith("Установка") || str.startsWith("Скважина")) && !str.startsWith("СкважинаР-104");
                        if (starts) {
                            for (WellNames wellNames : WellNames.values()) {
                                if (str.startsWith(wellNames.getName())) {
                                    type = wellNames.getType();
                                    name = wellNames.getCode();
                                }
                            }
                        }
                        break;
                    case NUMERIC:
                        double number = cell.getNumericCellValue();
                        int column = cell.getColumnIndex();
                        if (type.equals("withZones")) {
                            switch (column) {
                                case 0:
                                    String date = filename.substring(filename.lastIndexOf("AutoReport-")).replaceAll("AutoReport-", "");
                                    date = date.replaceAll("\\D", "");
                                    int year = Integer.parseInt(date.substring(0, 4));
                                    int month = Integer.parseInt(date.substring(4, 6));
                                    int day = Integer.parseInt(date.substring(6));
                                    int hour = (int) (cell.getNumericCellValue() - 2);
                                    int minute = 0;
                                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                                    int d = 0;
                                    well.setDateTime(dateTime);
                                    break;
                                case 1:
                                    well.setBufferPressure(cell.getNumericCellValue());
                                    break;
                                case 2:
                                    well.setOutCasingPrassure(cell.getNumericCellValue());
                                    break;
                                case 3:
                                    well.setLinearPressure(cell.getNumericCellValue());
                                    break;
                                case 15:
                                    well.setTemperatureGauge(cell.getNumericCellValue());
                                    break;
                                case 16:
                                    well.setPressureGauge(cell.getNumericCellValue());
                                    break;
                            }
                            well.setName(name);
                        }
                        if (type.equals("gaugesTP")) {
                            switch (column) {
                                case 0:
                                    String date = filename.substring(filename.lastIndexOf("AutoReport-")).replaceAll("AutoReport-", "");
                                    date = date.replaceAll("\\D", "");
                                    int year = Integer.parseInt(date.substring(0, 4));
                                    int month = Integer.parseInt(date.substring(4, 6));
                                    int day = Integer.parseInt(date.substring(6));
                                    int hour = (int) (cell.getNumericCellValue() - 2);
                                    int minute = 0;
                                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                                    int d = 0;
                                    well.setDateTime(dateTime);
                                    break;
                                case 1:
                                    well.setBufferPressure(cell.getNumericCellValue());
                                    break;
                                case 2:
                                    well.setOutCasingPrassure(cell.getNumericCellValue());
                                    break;
                                case 3:
                                    well.setLinearPressure(cell.getNumericCellValue());
                                    break;
                                case 7:
                                    well.setTemperatureGauge(cell.getNumericCellValue());
                                    break;
                                case 8:
                                    well.setPressureGauge(cell.getNumericCellValue());
                                    break;
                            }
                            well.setName(name);
                        }
                        if (type.equals("gaugesPT")) {
                            switch (column) {
                                case 0:
                                    String date = filename.substring(filename.lastIndexOf("AutoReport-")).replaceAll("AutoReport-", "");
                                    date = date.replaceAll("\\D", "");
                                    int year = Integer.parseInt(date.substring(0, 4));
                                    int month = Integer.parseInt(date.substring(4, 6));
                                    int day = Integer.parseInt(date.substring(6));
                                    int hour = (int) (cell.getNumericCellValue() - 2);
                                    int minute = 0;
                                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                                    int d = 0;
                                    well.setDateTime(dateTime);
                                    break;
                                case 1:
                                    well.setBufferPressure(cell.getNumericCellValue());
                                    break;
                                case 2:
                                    well.setOutCasingPrassure(cell.getNumericCellValue());
                                    break;
                                case 3:
                                    well.setLinearPressure(cell.getNumericCellValue());
                                    break;
                                case 8:
                                    well.setTemperatureGauge(cell.getNumericCellValue());
                                    break;
                                case 7:
                                    well.setPressureGauge(cell.getNumericCellValue());
                                    break;
                            }
                            well.setName(name);
                        }
                        if (type.equals("VP")) {
                            switch (column) {
                                case 0:
                                    String date = filename.substring(filename.lastIndexOf("AutoReport-")).replaceAll("AutoReport-", "");
                                    date = date.replaceAll("\\D", "");
                                    int year = Integer.parseInt(date.substring(0, 4));
                                    int month = Integer.parseInt(date.substring(4, 6));
                                    int day = Integer.parseInt(date.substring(6));
                                    int hour = (int) (cell.getNumericCellValue() - 2);
                                    int minute = 0;
                                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                                    int d = 0;
                                    well.setDateTime(dateTime);
                                    break;
                                case 2:
                                    well.setBufferPressure(cell.getNumericCellValue());
                                    break;
                                case 5:
                                    well.setTemperatureGauge(cell.getNumericCellValue());
                                    break;
                                case 6:
                                    well.setPressureGauge(cell.getNumericCellValue());
                                    break;
                            }
                            well.setName(name);
                            well.setLinearPressure(0);
                            well.setOutCasingPrassure(0);
                        }
                        if (type.equals("VP2")) {
                            switch (column) {
                                case 0:
                                    String date = filename.substring(filename.lastIndexOf("AutoReport-")).replaceAll("AutoReport-", "");
                                    date = date.replaceAll("\\D", "");
                                    int year = Integer.parseInt(date.substring(0, 4));
                                    int month = Integer.parseInt(date.substring(4, 6));
                                    int day = Integer.parseInt(date.substring(6));
                                    int hour = (int) (cell.getNumericCellValue() - 2);
                                    int minute = 0;
                                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                                    int d = 0;
                                    well.setDateTime(dateTime);
                                    break;
                                case 1:
                                    well.setBufferPressure(cell.getNumericCellValue());
                                    break;
                                case 2:
                                    well.setOutCasingPrassure(cell.getNumericCellValue());
                                    break;
                                case 5:
                                    well.setTemperatureGauge(cell.getNumericCellValue());
                                    break;
                                case 6:
                                    well.setPressureGauge(cell.getNumericCellValue());
                                    break;
                            }
                            well.setLinearPressure(0);
                            well.setName(name);
                        }
                }

            }
            if (well.getName()!=null&&well.getDateTime()!=null) {
                list.add(well);
            }
        }
        for(int i=0;i<list.size();i++){
            if(i%12==0){
                System.out.println("**********************"+list.get(i).getName()+"***************");
            }
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }
}