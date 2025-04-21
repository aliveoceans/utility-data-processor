package com.aliveoceans.dataprocessor.converter.writer;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YmlWriter  implements Writer{
    @Override
    public void write(List<Map<String, Object>> data, String outputFile) {
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        try (FileWriter writer = new FileWriter(outputFile)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
