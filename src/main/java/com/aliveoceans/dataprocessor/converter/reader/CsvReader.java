package com.aliveoceans.dataprocessor.converter.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvReader implements Reader {
    public List<Map<String, Object>> read(String path) {
        List<Map<String, Object>> rows = new ArrayList<>();

        try (java.io.Reader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : parser) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (String header : parser.getHeaderNames()) {
                    row.put(header, record.get(header));
                }
                rows.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rows;
    }
}
