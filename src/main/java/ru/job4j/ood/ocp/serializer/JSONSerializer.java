package ru.job4j.ood.ocp.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class JSONSerializer implements Serializer {
    @Override
    public String convert(List<Employee> report) {
        if (report == null) {
            throw new NullPointerException("передано null значение");
        }
        Employees employees = new Employees(report);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(employees.getEmployees());
    }

}
