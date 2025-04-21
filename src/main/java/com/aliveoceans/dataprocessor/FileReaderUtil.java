package com.aliveoceans.dataprocessor;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileReaderUtil {
    public static List<String[]> readCSV(String path) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void writeOutput(String path, List<String> lines) {
        try {
            Files.write(Paths.get(path), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}