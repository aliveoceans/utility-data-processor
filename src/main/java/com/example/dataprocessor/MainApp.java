package com.example.dataprocessor;

public class MainApp {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        processor.process("input/data.csv", "output/result.txt");
    }
}