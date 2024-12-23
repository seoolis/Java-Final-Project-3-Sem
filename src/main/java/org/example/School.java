package org.example;

public class School {
    public int row;
    public int district;
    public String school;
    public String county;
    public String grades;
    public int students;
    public float teachers;
    public float calworks;
    public float lunch;
    public int computer;
    public float expenditure;
    public float income;
    public float english;
    public float read;
    public float math;

    public School(int row, int district, String school, String county, String grades, int students, float teachers,
                  float calworks, float lunch, int computer, float expenditure, float income,
                  float english, float read, float math) {
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

    public int getRow() {
        return row;
    }

    public int getDistrict() {
        return district;
    }

    public String getSchool() {
        return school;
    }

    public String getCounty() {
        return county;
    }

    public String getGrades() {
        return grades;
    }

    public int getStudents() {
        return students;
    }

    public double getTeachers() {
        return teachers;
    }

    public double getCalworks() {
        return calworks;
    }

    public double getLunch() {
        return lunch;
    }

    public int getComputer() {
        return computer;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public double getIncome() {
        return income;
    }

    public double getEnglish() {
        return english;
    }

    public double getRead() {
        return read;
    }

    public double getMath() {
        return math;
    }

    @Override
    public String toString() {
        return "School{" +
                "row=" + row +
                ", district='" + district + '\'' +
                ", school='" + school + '\'' +
                ", county='" + county + '\'' +
                ", grades='" + grades + '\'' +
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
