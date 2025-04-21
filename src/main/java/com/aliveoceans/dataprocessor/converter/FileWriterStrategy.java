package com.aliveoceans.dataprocessor.converter;

import java.util.*;

public interface FileWriterStrategy {
    void write(List<Map<String, Object>> data, String outputPath) throws Exception;
}
