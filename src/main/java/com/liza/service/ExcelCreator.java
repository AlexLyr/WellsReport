package com.liza.service;

import com.liza.MainClass;
import com.liza.model.Well;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class ExcelCreator {
    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void createFile(String path,EntityService service) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Final report");

            int rownum = 0;
            Cell cell;
            Row row;
            //
            XSSFCellStyle style = createStyleForTitle(workbook);

            row = sheet.createRow(rownum);
        if(!MainClass.needOnlyJoinTablesForLast5) {

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Скважина");
            cell.setCellStyle(style);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Дата");
            cell.setCellStyle(style);

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue("Буферное давление");
            cell.setCellStyle(style);

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue("Затрубное давление");
            cell.setCellStyle(style);

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue("Давление на линии");
            cell.setCellStyle(style);

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue("Температура глубинные датчики");
            cell.setCellStyle(style);

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue("Давление глубинные датчики");
            cell.setCellStyle(style);

            for (Well well : service.getAllWells()) {
                rownum++;
                row = sheet.createRow(rownum);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(well.getName());

                cell = row.createCell(1, CellType.STRING);
                GregorianCalendar date = GregorianCalendar.from(ZonedDateTime.of(well.getDateTime().plusHours(1), ZoneId.systemDefault()));
                cell.setCellValue(date);

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(well.getBufferPressure());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(well.getOutCasingPrassure());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(well.getLinearPressure());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(well.getTemperatureGauge());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(well.getPressureGauge());
            }
        }
        else {
            for(Well well : service.getAllWells()) {
                rownum++;
                row = sheet.createRow(rownum);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(well.getName());

                cell = row.createCell(1, CellType.STRING);
                GregorianCalendar date = GregorianCalendar.from(ZonedDateTime.of(well.getDateTime().plusHours(1), ZoneId.systemDefault()));
                cell.setCellValue(date);
                for(int i = 2;i<well.getVals().size();i++) {
                    cell = row.createCell(i,CellType.NUMERIC);
                    cell.setCellValue(well.getVals().get(i-2));
                }
            }
        }

        File file = new File(path);
        file.getParentFile().mkdirs();

        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Created file: " + file.getAbsolutePath());

    }
}
