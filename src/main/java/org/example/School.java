package org.example;
public class School {
    private int row;
    private String district;
    private String school;
    private String county;
    private String grades;
    private int students;
    private double teachers;
    private double calworks;
    private double lunch;
    private int computer;
    private double expenditure;
    private double income;
    private double english;
    private double read;
    private double math;
    public School(int row, String district, String school, String county, String grades, int students, double teachers,
                  double calworks, double lunch, int computer, double expenditure, double income,
                  double english, double read, double math) {
        this.row = row;
        this.district = district;
        this.school = school;
        this.county = county;
        this.grades = grades;
        this.students = students;
        this.teachers = teachers;
        this.calworks = calworks;
        this.lunch = lunch;
        this.computer = computer;
        this.expenditure = expenditure;
        this.income = income;
        this.english = english;
        this.read = read;
        this.math = math;
    }
    @Override
    public String toString() {
        return "School{" +
                "row=" + row +
                ", district=" + district +
                ", school=" + school +
                ", county=" + county +
                ", grades=" + grades +
                ", students=" + students +
                ", teachers=" + teachers +
                ", calworks=" + calworks +
                ", lunch=" + lunch +
                ", computer=" + computer +
                ", expenditure=" + expenditure +
                ", income=" + income +
                ", english=" + english +
                ", read=" + read +
                ", math=" + math +
                '}';
    }
}