package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class DatabaseQueries {

    private static Connection conn;

    public DatabaseQueries(Connection conn) {
        this.conn = conn;
    }

    // Задание 1
    public void plotAverageStudentsByCounty() {
        String sql = "SELECT county, students FROM schools";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            Map<String, Integer> countyTotals = new HashMap<>();
            Map<String, Integer> countyCounts = new HashMap<>();

            while (rs.next()) {
                String county = rs.getString("county");
                int students = rs.getInt("students");

                countyTotals.put(county, countyTotals.getOrDefault(county, 0) + students);
                countyCounts.put(county, countyCounts.getOrDefault(county, 0) + 1);
            }

            Map<String, Double> averageStudentsByCounty = new TreeMap<>();
            for (String county : countyTotals.keySet()) {
                double average = (double) countyTotals.get(county) / countyCounts.get(county);
                averageStudentsByCounty.put(county, average);
            }

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            averageStudentsByCounty.entrySet().stream()
                    .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                    .limit(10)
                    .forEach(entry -> {
                        dataset.addValue(entry.getValue(), "Average Students", entry.getKey());
                    });

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Average Students by County",
                    "County",
                    "Average Students",
                    dataset
            );

            displayChart(barChart); // отображение графика
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    // Задание 2
    public void printAverageExpenditureForSpecificCounties() {
        String sql = """
                SELECT county, AVG(expenditure) AS average_expenditure, SUM(expenditure) AS total_expenditure
                FROM schools
                WHERE TRIM(LOWER(county)) IN (LOWER('Fresno'), LOWER('Contra Costa'), LOWER('El Dorado'), LOWER('Glenn'))
                AND expenditure > 10
                GROUP BY county
                """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean hasResults = false; // Флаг, чтобы понять, есть ли результаты
            System.out.println("Средние расходы и сумма расходов по округам (Fresno, Contra Costa, El Dorado, Glenn), где расходы больше 10:");

            while (rs.next()) {
                hasResults = true; // Если данные найдены
                String county = rs.getString("county");
                double averageExpenditure = rs.getDouble("average_expenditure");
                double totalExpenditure = rs.getDouble("total_expenditure");

                // Выводим округ, средние расходы и сумму расходов
                System.out.printf("Округ: %s, Средние расходы: %.2f, Сумма расходов: %.2f%n", county, averageExpenditure, totalExpenditure);
            }

            if (!hasResults) {
                System.out.println("Данные для указанных округов с расходами больше 10 отсутствуют.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }


    // Задание 3
    public void printSchoolsWithHighestMathScore() {
        // Запрос для школ с наибольшим баллом по математике в первом диапазоне
        String sql = """
                SELECT school, students, math
                FROM schools
                WHERE students BETWEEN 5000 AND 7500
                ORDER BY math DESC
                LIMIT 1
                """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Школы с самым высоким баллом по математике для диапазона 5000-7500 студентов:");

            if (rs.next()) {
                String school = rs.getString("school");
                int students = rs.getInt("students");
                double math = rs.getDouble("math");

                System.out.printf("Школа: %s, Студенты: %d, Балл по математике: %.2f%n", school, students, math);
            } else {
                System.out.println("Школы в диапазоне 5000-7500 студентов с максимальным баллом по математике не найдены.");
            }

            // Перезапускаем запрос для второго диапазона
            sql = """
                    SELECT school, students, math
                    FROM schools
                    WHERE students BETWEEN 10000 AND 11000
                    ORDER BY math DESC
                    LIMIT 1
                    """;

            // Здесь больше не нужно использовать финальную переменную rs, так как она перезаписывается
            try (ResultSet rs2 = stmt.executeQuery(sql)) {
                System.out.println("Школы с самым высоким баллом по математике для диапазона 10000-11000 студентов:");

                if (rs2.next()) {
                    String school = rs2.getString("school");
                    int students = rs2.getInt("students");
                    double math = rs2.getDouble("math");

                    System.out.printf("Школа: %s, Студенты: %d, Балл по математике: %.2f%n", school, students, math);
                } else {
                    System.out.println("Школы в диапазоне 10000-11000 студентов с максимальным баллом по математике не найдены.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    // Вспомогательный метод для отображения графиков
    private void displayChart(JFreeChart chart) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.setVisible(true);
    }
}