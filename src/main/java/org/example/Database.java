package org.example;

import java.sql.*;
import java.util.List;

public class Database {
    private Connection conn;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:schools.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Подключение установлено.");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер не найден: " + e.getMessage());
        }
    }

    public void createTables() {
        String sql = """
                CREATE TABLE IF NOT EXISTS schools (
                    id INTEGER PRIMARY KEY,
                    district TEXT,
                    school TEXT,
                    county TEXT,
                    grades TEXT,
                    students INTEGER,
                    teachers REAL,
                    calworks REAL,
                    lunch REAL,
                    computer INTEGER,
                    expenditure INTEGER,
                    income REAL,
                    english REAL,
                    read REAL,
                    math REAL
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица успешно создана.");
        } catch (SQLException e) {
            System.err.println("Ошибка создания таблицы: " + e.getMessage());
        }
    }

    public void dropTables() {
        String sql = "DROP TABLE IF EXISTS schools";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица успешно удалена.");
        } catch (SQLException e) {
            System.err.println("Ошибка удаления таблицы: " + e.getMessage());
        }
    }

    public void insertSchools(List<School> schools) {
        String insertSQL = """
                INSERT INTO schools (id, district, school, county, grades, students, teachers, calworks, lunch, computer, expenditure, income, english, read, math)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (School school : schools) {
                pstmt.setInt(1, school.getRow());
                pstmt.setInt(2, school.getDistrict());
                pstmt.setString(3, school.getSchool());
                pstmt.setString(4, school.getCounty());
                pstmt.setString(5, school.getGrades());
                pstmt.setInt(6, school.getStudents());
                pstmt.setDouble(7, school.getTeachers());
                pstmt.setDouble(8, school.getCalworks());
                pstmt.setDouble(9, school.getLunch());
                pstmt.setInt(10, school.getComputer());
                pstmt.setDouble(11, school.getExpenditure());
                pstmt.setDouble(12, school.getIncome());
                pstmt.setDouble(13, school.getEnglish());
                pstmt.setDouble(14, school.getRead());
                pstmt.setDouble(15, school.getMath());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Данные успешно добавлены в базу данных.");
        } catch (SQLException e) {
            System.err.println("Ошибка вставки данных: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }
}