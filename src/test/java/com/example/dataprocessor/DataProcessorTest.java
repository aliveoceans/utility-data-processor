package com.example.dataprocessor;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DataProcessorTest {
    @Test
    public void testCSVRead() {
        List<String[]> data = FileReaderUtil.readCSV("input/data.csv");
        assertFalse(data.isEmpty());
    }
}