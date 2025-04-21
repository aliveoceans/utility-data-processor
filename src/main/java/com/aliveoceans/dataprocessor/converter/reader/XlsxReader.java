package com.aliveoceans.dataprocessor.converter.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class XlsxReader  implements Reader{
    @Override
    public List<Map<String, Object>> read(String path) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(Paths.get(path).toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            List<String> headers = new ArrayList<>();
            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue());
                }
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, Object> map = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    map.put(headers.get(i), getCellValue(cell));
                }
                rows.add(map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rows;
    }

    private Object getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case _NONE -> null;
            case NUMERIC -> (DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            case ERROR -> null;
        };
    }
}
