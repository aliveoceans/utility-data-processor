package com.aliveoceans.dataprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;

public class FileValidator {
    private static final Set<String> SUPPORTED_TYPES = Set.of("csv", "json", "xlsx", "yml", "xml");

    public static void validate(String filePath, String fileType) throws Exception {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        if (!SUPPORTED_TYPES.contains(fileType)) {
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        if (!filePath.toLowerCase(Locale.ROOT).endsWith(fileType.toLowerCase(Locale.ROOT))){
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        System.out.println("File and type validated successfully.");
    }
}