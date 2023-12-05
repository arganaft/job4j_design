package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Student {
    private final int course;
    private final boolean stateEmployee;
    private final String name;
    private final Contact contact;
    private final String[] subjects;

    public Student(int course, boolean stateEmployee, String name, Contact contact, String[] subjects) {
        this.course = course;
        this.stateEmployee = stateEmployee;
        this.name = name;
        this.contact = contact;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{"
                + "course=" + course
                + ", stateEmployee=" + stateEmployee
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", subjects=" + Arrays.toString(subjects)
                + '}';
    }

    public static void main(String[] args) {
        final Student student1 = new Student(3,
                true,
                "Steve",
                new Contact("81234567890"),
                new String[] {"mathematics", "history", "cybernetics"});
        System.out.println(student1);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student1));
        final String student1ToGson = gson.toJson(student1);
        Student studentFromGson = gson.fromJson(student1ToGson, Student.class);
        System.out.println(studentFromGson);
    }
}
