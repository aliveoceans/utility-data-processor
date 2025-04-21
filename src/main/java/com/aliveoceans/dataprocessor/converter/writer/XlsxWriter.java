package com.aliveoceans.dataprocessor.converter.writer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XlsxWriter  implements Writer{
    @Override
    public void write(List<Map<String, Object>> data, String outputPath)  {
        if (data.isEmpty()) throw new IllegalArgumentException("No data to write.");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        Row headerRow = sheet.createRow(0);
        List<String> headers = new ArrayList<>(data.get(0).keySet());
        for (int i = 0; i < headers.size(); i++) {
            headerRow.createCell(i).setCellValue(headers.get(i));
        }

        for (int rowNum = 0; rowNum < data.size(); rowNum++) {
            Row row = sheet.createRow(rowNum + 1);
            Map<String, Object> rowData = data.get(rowNum);
            for (int col = 0; col < headers.size(); col++) {
                Object value = rowData.get(headers.get(col));
                row.createCell(col).setCellValue(value != null ? value.toString() : "");
            }
        }

        try (FileOutputStream fos = new FileOutputStream(Paths.get(outputPath).toFile())) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
