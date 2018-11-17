package com.liza;

import com.liza.dao.DaoCreator;
import com.liza.service.EntityService;
import com.liza.service.ExcelCreator;

public class MainClass {
    private static final String DIRECTORY = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\08";
    private static final String[] SHEETS = {"Скважины МЛСП БК","Скважины МЛСП"};
    private static final String RESULT_FILE = "C:\\Users\\SPIDER\\Desktop\\Lukoil\\finalReport5.xlsx";
    public static boolean needOnlyJoinTablesForLast5 = true;

    public static void main(String[] args){
        DaoCreator creator = DaoCreator.getCreator();
        creator.fillStorage(DIRECTORY);
        EntityService service = new EntityService(SHEETS);
        service.fillObjectsFromAllFiles();
        ExcelCreator.createFile(RESULT_FILE,service);
        System.out.println(service.getAllWells().size());
    }
}
