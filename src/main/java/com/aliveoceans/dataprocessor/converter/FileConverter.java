package com.aliveoceans.dataprocessor.converter;

import com.aliveoceans.dataprocessor.InputDataProcessor;
import com.aliveoceans.dataprocessor.converter.writer.Writer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileConverter {
    public static void convert(String inputPath, SupportedTypes inputType, SupportedTypes outputType) throws Exception {
        // Step 1: Read input
        List<Map<String, Object>> data = InputDataProcessor.read(inputPath, inputType);

        // Step 2: Generate output path
        Path input = Paths.get(inputPath);
        String fileNameWithoutExt = input.getFileName().toString().replaceFirst("[.][^.]+$", "");
        String outputFile = input.getParent() + "/" + fileNameWithoutExt + "_converted." + outputType;

        // Step 3: Write output
        Writer writer = FileConverterFactory.getWriter(outputType);
        writer.write(data, outputFile);

        System.out.println("✅ Output file generated at: " + outputFile);
    }
}
