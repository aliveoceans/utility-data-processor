package com.aliveoceans.dataprocessor.converter.writer;


import java.util.List;
import java.util.Map;

public interface Writer {
    void write(List<Map<String, Object>> data, String outputFile);
}
