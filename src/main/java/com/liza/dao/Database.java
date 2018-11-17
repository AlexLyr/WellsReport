package com.liza.dao;

import com.liza.model.Well;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {

    public static Map<String, XSSFWorkbook> storage = new ConcurrentHashMap<>();
    public static List<Well> wellsList= new ArrayList<>();
    public enum WellNames{
        P11("���������-11","withZones","11"),
        P12("���������-12","gaugesTP","12"),
        P13("��������13","withZones","13"),
        P14("���������-14","gaugesPT","14"),
        P15("��������15","gaugesPT","15"),
        P16("��������16","gaugesTP","16"),
        P101("��������101","gaugesTP","101"),
        P102("��������102","gaugesTP","102"),
        P103("��������103","gaugesTP","103"),
        P104("��������104","gaugesPT","104"),
        P105("��������105","gaugesTP","105"),
        P106("��������106","gaugesTP","106"),
        P107("��������107","gaugesTP","107"),
        P108("��������108","gaugesTP","108"),
        P109("��������109","gaugesTP","109"),
        P110("��������110","gaugesTP","110"),
        P113("��������113","gaugesTP","113"),
        P114("��������114","gaugesTP","114"),
        P115("��������115","gaugesTP","115"),
        P116("��������116","gaugesTP","116"),
        P117("��������117","gaugesPT","117"),
        P120("��������120","gaugesTP","120"),
        P121("��������121","gaugesTP","121"),
        P122("��������122","gaugesTP","122"),
        P311("��������311","gaugesTP","311"),
        G1("��������G-1","gaugesTP","G01"),
        G1BIS("��������G-1���","gaugesTP","G01bis"),
        VP1("����������������������������������-1","VP","VP_1"),
        VP2("����������-2","VP2","VP_2"),
        VP3("����������������������������������-3","VP","VP_3");

        private final String name;
        private final String type;
        private final String code;
        WellNames(String name, String type, String code) { this.name = name; this.type = type; this.code = code; }
        public String getName() { return name;}
        public String getType() { return type;}
        public String getCode() {return  code;}
    }
}
