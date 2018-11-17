package com.liza.dao;

import com.liza.model.Well;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DaoCreator {
    private static volatile DaoCreator instance;
    private DaoCreator(){}
    private void put(String name, XSSFWorkbook book){
        Database.storage.putIfAbsent(name,book);
    }

    public void fillStorage(String directoryName){
        BookReader reader = new BookReader();
        try {
            Files.walkFileTree(Paths.get(directoryName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = file.toAbsolutePath().toString();
                    DaoCreator.this.put(fileName,reader.read(fileName));
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DaoCreator getCreator() {
        DaoCreator localInstance = instance;
        if(localInstance == null ){
            synchronized (DaoCreator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoCreator();
                }
            }
        }
        return localInstance;
    }

}
