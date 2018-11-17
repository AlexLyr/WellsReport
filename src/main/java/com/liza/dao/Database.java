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
        P311("Скважина311","gaugesTP","311"),
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
}
