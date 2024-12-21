package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {
    public static List<School> readSchoolsFromCSV(String filePath) {
        List<School> schools = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Parser.class.getClassLoader().getResourceAsStream(filePath))))) {
            String line;
            boolean isFirstLine = true;
            int rowCounter = 0;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                rowCounter++;
                String[] values = line.split(",");
                try {
                    School school = new School(
                            rowCounter,
                            values[1],
                            values[2],
                            values[3],
                            values[4],
                            parseInteger(values[5]),
                            parseDouble(values[6]),
                            parseDouble(values[7]),
                            parseDouble(values[8]),
                            parseInteger(values[9]),
                            parseDouble(values[10]),
                            parseDouble(values[11]),
                            parseDouble(values[12]),
                            parseDouble(values[13]),
                            parseDouble(values[14])
                    );
                    schools.add(school);
                } catch (Exception e) {
                    System.err.println("Ошибка обработки линии: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Файл не найден: " + filePath);
        }
        return schools;
    }
    private static int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}