package com.aliveoceans.dataprocessor.converter;

import com.aliveoceans.dataprocessor.converter.reader.*;
import com.aliveoceans.dataprocessor.converter.writer.*;

import java.security.InvalidParameterException;

public enum SupportedTypes {
    XML("xml"),
    CSV("csv"),
    JSON("json"),
    YML("yml"),
    XLSX("xlsx");
    private String type;
    private Reader reader;
    private Writer writer;

    SupportedTypes(String type) {
        this.type = type;
        switch (type){
            case "xml": this.reader = new XmlReader(); this.writer = new XmlWriter(); break;
            case "csv": this.reader = new CsvReader(); this.writer = new CsvWriter(); break;
            case "json": this.reader = new JsonReader(); this.writer = new JsonWriter(); break;
            case "yml": this.reader = new YmlReader(); this.writer = new YmlWriter(); break;
            case "xlsx": this.reader = new XlsxReader(); this.writer = new XlsxWriter(); break;
        }
    }

    public String getType() {
        return type;
    }

    public Reader getReader(){
        return this.reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public static SupportedTypes get(String type) throws InvalidParameterException{
        return switch (type) {
            case "xml" -> XML;
            case "csv" -> CSV;
            case "json" -> JSON;
            case "yml" -> YML;
            case "xlsx" -> XLSX;
            default -> throw new InvalidParameterException("Unsupported type: " + type);
        };
    }

}
