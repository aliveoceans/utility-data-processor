package com.aliveoceans.dataprocessor.converter.reader;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YmlReader implements Reader{
    @Override
    public List<Map<String, Object>> read(String path) {
        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
        try (InputStream input = new FileInputStream(path)) {
            Object data = yaml.load(input);
            if (data instanceof List<?> list) {
                // If already a list of maps
                if (!list.isEmpty() && list.get(0) instanceof Map) {
                    return (List<Map<String, Object>>) list;
                }
            } else if (data instanceof Map<?, ?> map) {
                // Wrap single map into a list
                return List.of((Map<String, Object>) map);
            }
            throw new IllegalArgumentException("Unsupported YAML structure. Expected a list or map.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
