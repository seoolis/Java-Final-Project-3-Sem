package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Float.parseFloat;

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

                // Разбираем строку с учётом экранированных кавычек
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Регулярное выражение для экранированных значений

                try {
                    // Удаляем кавычки из значений перед преобразованием в числа
                    values = removeQuotes(values);

                    // Преобразуем значения из массива в объект School
                    School school = new School(
                            rowCounter,
                            parseInteger(values[1]),
                            values[2],
                            values[3],
                            values[4],
                            parseInteger(values[5]),
                            parseFloat(values[6]),
                            parseFloat(values[7]),
                            parseFloat(values[8]),
                            parseInteger(values[9]),
                            parseFloat(values[10]),    // expenditure
                            parseFloat(values[11]),    // income
                            parseFloat(values[12]),    // english
                            parseFloat(values[13]),    // read
                            parseFloat(values[14])
                    );
                    schools.add(school);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Ошибка при разборе строки: " + line + " | " + e.getMessage());
                }
            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        return schools;
    }

    // Метод для удаления кавычек из значений массива
    private static String[] removeQuotes(String[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].replaceAll("\"", "").trim();  // Убираем кавычки и лишние пробелы
        }
        return values;
    }

    // Метод для парсинга целых чисел, учитывая пустые или некорректные значения
    private static int parseInteger(String value) {
        return value != null && !value.trim().isEmpty() ? Integer.parseInt(value.trim()) : 0;
    }

    // Метод для парсинга дробных чисел с защитой от ошибок
    private static double parseDouble(String value) {
        return value != null && !value.trim().isEmpty() ? Double.parseDouble(value.trim()) : 0.0;
    }
}
