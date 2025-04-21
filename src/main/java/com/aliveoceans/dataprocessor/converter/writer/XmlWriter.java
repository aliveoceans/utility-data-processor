package com.aliveoceans.dataprocessor.converter.writer;

import java.util.List;
import java.util.Map;

public class XmlWriter  implements Writer{
    @Override
    public void write(List<Map<String, Object>> data, String outputFile) {
        System.out.println("Writing for : " + outputFile);
        // TODO: to be implemented
    }
}
