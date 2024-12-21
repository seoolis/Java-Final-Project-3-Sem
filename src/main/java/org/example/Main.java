package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "Schools.csv";
        List<School> schools = Parser.readSchoolsFromCSV(filePath);

        for (School school : schools) {
            System.out.println(school);
        }
    }
}