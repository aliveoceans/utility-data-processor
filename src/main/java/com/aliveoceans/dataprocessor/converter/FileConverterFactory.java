package com.aliveoceans.dataprocessor.converter;

import com.aliveoceans.dataprocessor.converter.writer.Writer;

public class FileConverterFactory {
    public static Writer getWriter(SupportedTypes type) {
        return type.getWriter();
    }
}
