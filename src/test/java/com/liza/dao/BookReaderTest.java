package com.liza.dao;

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
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookReaderTest {
    private static final String filename = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\08\\AutoReport-20180801.xlsx";
    private static final String directoryName = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\08";
    private static final String sheetName = "Ñêâàæèíû ÌËÑÏ";
    public static Map<String, XSSFWorkbook> testStorage = new ConcurrentHashMap<>();

    @Test
    void read() {
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
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellTypeEnum();

                switch (cellType) {
                    case _NONE:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("\t");
                        break;
                    case BLANK:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case FORMULA:
                        // Formula
                        System.out.print(cell.getCellFormula());
                        System.out.print("\t");

                        FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();
                        // Print out value evaluated by formula
                        System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("\t");
                        break;
                    case ERROR:
                        System.out.print("!");
                        System.out.print("\t");
                        break;
                }

            }
            System.out.println("");
        }
    }

    @Test
    void visit() {
        BookReader reader = new BookReader();
        try {
            Files.walkFileTree(Paths.get(directoryName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = file.toAbsolutePath().toString();
                    testStorage.put(fileName,reader.read(fileName));
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(testStorage.size());
    }
}