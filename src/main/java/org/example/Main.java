package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Путь к CSV-файлу с данными о школах
        String filePath = "Schools.csv";

        // Считывание данных о школах из CSV
        List<School> schools = Parser.readSchoolsFromCSV(filePath);  // Метод для чтения данных из файла

        // Создание объекта для работы с базой данных
        Database db = new Database();  // Класс для работы с базой данных
        db.connect();  // Подключение к базе данных

        // Создание таблиц в базе данных (если они не существуют)
        db.dropTables();  // Удаление старых таблиц, если они существуют
        db.createTables();  // Создание новых таблиц в базе данных

        // Вставка данных о школах в базу данных
        db.insertSchools(schools);  // Вставка считанных школ в базу данных

        // Создание объекта для выполнения запросов
        DatabaseQueries queries = new DatabaseQueries(db.getConnection());  // Создание объекта для работы с запросами

        // Задание 1: Построение графика среднего количества студентов по округам
        queries.plotAverageStudentsByCounty();

        // Задание 2: Вывод среднего расхода по округам
        queries.printAverageExpenditureForSpecificCounties();

        // Задание 3: Вывод школ с самым высоким баллом по математике
        queries.printSchoolsWithHighestMathScore();
    }
}