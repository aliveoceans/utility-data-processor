package com.aliveoceans.dataprocessor;

import java.util.List;

public class DataProcessor {
    public void process(String inputPath, String outputPath) {
        List<String[]> records = FileReaderUtil.readCSV(inputPath);
        List<String> result = records.stream()
            .filter(r -> r.length >= 2 && Integer.parseInt(r[1]) > 18)
            .map(r -> "Name: " + r[0].toUpperCase() + ", Age: " + r[1])
            .toList();
        FileReaderUtil.writeOutput(outputPath, result);
    }
}