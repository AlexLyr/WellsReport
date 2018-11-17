package com.liza.service;

import com.liza.MainClass;
import com.liza.dao.Database;
import com.liza.model.Well;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EntityService {

    private String[] sheets;

    public EntityService(String[] sheets) {
        this.sheets = sheets;
    }

    public void fillObjectsFromAllFiles(){
        Database.storage.forEach(this::fillWithObjects);
    }
    public List<Well> getAllWells(){
        return Database.wellsList.stream()
                .sorted(Comparator.comparing(Well::getName).thenComparing(Well::getDateTime))
                .collect(Collectors.toList());
    }

    private void fillWithObjects(String filename, XSSFWorkbook book) {
        String type = "";
        String name = "";
        for (String sheetName : sheets) {
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
                    Float valz= null;
                    switch (cellType) {
                        case STRING:
                            try {
                                valz = Float.parseFloat(cell.getStringCellValue());
                                well.addVal(valz);
                            }
                            catch (Exception e) {}
                            String str = cell.getStringCellValue().replaceAll(" ", "");
                            boolean starts = (str.startsWith("Установка") || str.startsWith("Скважина")) && !str.startsWith("СкважинаР-104");
                            if (starts) {
                                for (Database.WellNames wellNames : Database.WellNames.values()) {
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
                            if (!MainClass.needOnlyJoinTablesForLast5) {
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
                            } else {
                                if(name.equals("G01")||name.equals("G01bis")||name.equals("VP_1")||name.equals("VP_2")||name.equals("VP_3")) {
                                    if(cell.getColumnIndex()==0) {
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
                                    }
                                    well.setName(name);
                                    well.addVal((float) cell.getNumericCellValue());
                                }
                            }
                    }
                }
                if (well.getName() != null && well.getDateTime() != null) {
                    Database.wellsList.add(well);
                }
            }
        }
    }

}
