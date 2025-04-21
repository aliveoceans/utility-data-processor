package com.aliveoceans.dataprocessor.converter.writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonWriter  implements Writer{
    @Override
    public void write(List<Map<String, Object>> data, String outputFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
