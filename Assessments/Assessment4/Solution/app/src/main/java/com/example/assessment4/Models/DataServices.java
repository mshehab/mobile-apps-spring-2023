package com.example.assessment4.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DataServices {
    public static ArrayList<Student> getStudents() {
        if(students == null){
            setupData();
        }
        return students;
    }

    private static ArrayList<Student> students;

    private static void setupData(){
        students = new ArrayList<>();
        students.add(new Student("Nevin Hobden",22,"Male","Computer Science","New Mexico","NM"));
        students.add(new Student("Stillman Macken",21,"Male","Computer Science","Arizona","AZ"));
        students.add(new Student("Stevy Ranscomb",24,"Male","Computer Science","Arizona","AZ"));
        students.add(new Student("Lynelle Garstang",18,"Female","Software & Information Systems","Nebraska","NE"));
        students.add(new Student("Worth Tomek",19,"Male","Software & Information Systems","Missouri","MO"));
        students.add(new Student("Bob Mendonca",20,"Male","Software & Information Systems","New York","NY"));
        students.add(new Student("Serene Leeds",21,"Female","Computer Science","California","CA"));
        students.add(new Student("Carin Everley",23,"Female","Data Science","Wisconsin","WI"));
        students.add(new Student("Beau Mangham",19,"Male","Software & Information Systems","Louisiana","LA"));
        students.add(new Student("Blithe Sizey",21,"Female","Computer Science","Pennsylvania","PA"));
        students.add(new Student("Rhett Bootland",22,"Male","Software & Information Systems","Florida","FL"));
        students.add(new Student("Titos Ashwood",23,"Male","Software & Information Systems","Minnesota","MN"));
        students.add(new Student("Caty Chatteris",24,"Female","Software & Information Systems","California","CA"));
        students.add(new Student("Taber Gavaghan",21,"Male","Software & Information Systems","Iowa","IA"));
        students.add(new Student("Sadella Meth",19,"Female","Computer Science","Tennessee","TN"));
        students.add(new Student("Patin Asch",20,"Male","Data Science","Texas","TX"));
        students.add(new Student("Eilis Ratchford",18,"Female","Data Science","Florida","FL"));
        students.add(new Student("Sammy Scorton",21,"Female","Data Science","Nevada","NV"));
        students.add(new Student("Mylo Cropp",19,"Male","Computer Science","Michigan","MI"));
        students.add(new Student("Jessie Brabham",21,"Male","Software & Information Systems","New York","NY"));
        students.add(new Student("Ransom Van Hesteren",22,"Male","Data Science","Georgia","GA"));
        students.add(new Student("Lonnie Lefeuvre",23,"Male","Computer Science","Florida","FL"));
        students.add(new Student("Eddi Labbett",24,"Female","Computer Science","Washington","WA"));
        students.add(new Student("Muffin Burdoun",20,"Male","Software & Information Systems","Florida","FL"));
        students.add(new Student("Richy Siverns",19,"Male","Software & Information Systems","Illinois","IL"));
        students.add(new Student("Benjie O'Henery",18,"Male","Software & Information Systems","Indiana","IN"));
        students.add(new Student("Loni Snowden",22,"Female","Data Science","California","CA"));
        students.add(new Student("Thorn Duffet",21,"Male","Data Science","Texas","TX"));
        students.add(new Student("Madelyn Ornells",20,"Female","Software & Information Systems","Ohio","OH"));
        students.add(new Student("Lulu Oppie",22,"Female","Computer Science","Maryland","MD"));
        students.add(new Student("Heath Hadkins",21,"Male","Computer Science","Florida","FL"));
        students.add(new Student("Lukas Frensch",19,"Male","Data Science","Texas","TX"));
        students.add(new Student("Julieta Kear",18,"Female","Computer Science","Florida","FL"));
        students.add(new Student("Mahalia Margaritelli",21,"Female","Computer Science","Oklahoma","OK"));
        students.add(new Student("Blair Hansberry",22,"Female","Software & Information Systems","Virginia","VA"));
        students.add(new Student("Guthrey Thomkins",22,"Male","Software & Information Systems","Massachusetts","MA"));
        students.add(new Student("Erny Dayment",23,"Male","Data Science","Florida","FL"));
        students.add(new Student("Krystal Bullcock",25,"Female","Software & Information Systems","Kansas","KS"));
        students.add(new Student("Min Perroni",22,"Female","Software & Information Systems","Connecticut","CT"));
        students.add(new Student("Jessica Spurrier",21,"Female","Data Science","Arkansas","AR"));


        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Computer Science Principles", "ITSC 1110", 3.0));
        courses.add(new Course("Introduction to Computer Science I", "ITSC 1212", 4.0));
        courses.add(new Course("Introduction to Computer Science II", "ITSC 1213", 4.0));
        courses.add(new Course("Computing Professionals", "ITSC 1600", 2.0));
        courses.add(new Course("Logic and Algorithms", "ITSC 2175", 3.0));
        courses.add(new Course("Introduction to Computer Systems", "ITSC 2181", 4.0));
        courses.add(new Course("Data Structures and Algorithms", "ITSC 2214", 3.0));
        courses.add(new Course("Software Development Projects", "ITSC 4155", 3.0));
        courses.add(new Course("Web-Based Application Design and Development", "ITIS 3135", 3.0));
        courses.add(new Course("Advanced Client Applications", "ITIS 4170", 3.0));
        courses.add(new Course("Mobile Application Development", "ITIS 4180", 3.0));
        courses.add(new Course("Secure Programming and Penetration Testing", "ITIS 4221", 3.0));
        courses.add(new Course("Web Mining", "ITIS 4510", 3.0));
        courses.add(new Course("Usable Security and Privacy", "ITIS 4420", 3.0));

        Random random = new Random(10389484);
        Collections.shuffle(courses, random);

        String[] letters = {"A", "B", "C", "D", "F"};
        for (Student student : students) {
            for (int i = 0; i < courses.size(); i++) {
                if(random.nextBoolean()){
                    Course course = courses.get(i);
                    String letter = letters[random.nextInt(letters.length)];
                    student.getCourses().add(new CourseHistory(course.getName(), course.getNumber(), letter, course.getHours()));
                }
            }
        }
    }

    private static class Course{
        String name, number;
        Double hours;

        public Course() {
        }

        public Course(String name, String number, Double hours) {
            this.name = name;
            this.number = number;
            this.hours = hours;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Double getHours() {
            return hours;
        }

        public void setHours(Double hours) {
            this.hours = hours;
        }
    }
}
