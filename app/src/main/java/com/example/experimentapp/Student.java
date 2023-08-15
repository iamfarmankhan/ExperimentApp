package com.example.experimentapp;

public class Student implements Comparable{

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
       if (this.age>student.age){
           return 1;
       }
       else if (this.age< student.age){
           return -1;
       }
       else {
           return 0;
       }

    }
}
