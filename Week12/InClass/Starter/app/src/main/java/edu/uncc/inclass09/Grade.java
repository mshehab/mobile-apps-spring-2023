package edu.uncc.inclass09;

public class Grade {
    public double courseHours;
    public String letterGrade;
    public String courseName;
    public String courseNumber;

    public Grade() {
    }

    public double getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(double courseHours) {
        this.courseHours = courseHours;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
}
