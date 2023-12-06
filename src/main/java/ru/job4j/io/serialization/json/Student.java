package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private int course;

    @XmlAttribute
    private boolean stateEmployee;

    @XmlAttribute
    private String name;
    private Contact contact;
    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private String[] subjects;

    public int getCourse() {
        return course;
    }

    public boolean isStateEmployee() {
        return stateEmployee;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public Student() { }

    public Student(int course, boolean stateEmployee, String name, Contact contact, String... subjects) {
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


    public static void main(String[] args) throws JAXBException {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"81234567890\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("mathematics");
        list.add("history");
        list.add("cybernetics");
        JSONArray jsonSubjects = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Student student = new Student(3,
                true,
                "Steve",
                new Contact("81234567890"),
                "mathematics", "history", "cybernetics");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("course", student.course);
        jsonObject.put("stateEmployee", student.stateEmployee);
        jsonObject.put("stateEmployee", student.stateEmployee);
        jsonObject.put("name", student.name);
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonSubjects);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект student в json-строку */
        System.out.println(new JSONObject(student).toString());
    }
}
