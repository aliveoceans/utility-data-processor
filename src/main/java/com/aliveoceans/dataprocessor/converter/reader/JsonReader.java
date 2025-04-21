package com.aliveoceans.dataprocessor.converter.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonReader  implements Reader{
    @Override
    public List<Map<String, Object>> read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(path);

        if (!jsonFile.exists()) {
            throw new IllegalArgumentException("JSON file not found: " + path);
        }

        try {
            return mapper.readValue(jsonFile, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
