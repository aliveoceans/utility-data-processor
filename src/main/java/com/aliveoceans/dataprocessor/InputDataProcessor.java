package com.aliveoceans.dataprocessor;

import com.aliveoceans.dataprocessor.converter.SupportedTypes;
import com.aliveoceans.dataprocessor.converter.reader.*;

import java.util.List;
import java.util.Map;

public class InputDataProcessor {

    public static List<Map<String, Object>> read(String path, SupportedTypes type) throws Exception {
        return type.getReader().read(path);
    }
}
