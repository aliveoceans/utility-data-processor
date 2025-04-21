package com.aliveoceans.dataprocessor.converter.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CsvWriter implements Writer{
    @Override
    public void write(List<Map<String, Object>> data, String outputFile) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data to write to CSV.");
        }

        try (java.io.Writer writer = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_8)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(data.get(0).keySet().toArray(new String[0])));
            for (Map<String, Object> row : data) {
                printer.printRecord(row.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
