package ru.job4j.ood.ocp.serializer;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@XmlRootElement
public class Employees {
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    private List<FormattedEmployee> employees;

    public Employees() { }

    public Employees(List<Employee> report) {

        this.employees = formatEmployees(report);
    }

    @XmlElement(name = "employee")
    public List<FormattedEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = formatEmployees(employees);
    }

    private List<FormattedEmployee> formatEmployees(List<Employee> report) {
        List<FormattedEmployee> formatEmployees = new ArrayList<>();
        for (Employee employee : report) {
            formatEmployees.add(new FormattedEmployee(
                    employee.getName(),
                    parser.parse(employee.getHired()),
                    parser.parse(employee.getFired()),
                    employee.getSalary()
            ));
        }
        return formatEmployees;
    }

    public static class FormattedEmployee {
        String name;
        String hired;
        String fired;
        double salary;

        public FormattedEmployee(String name, String hired, String fired, double salary) {
            this.name = name;
            this.hired = hired;
            this.fired = fired;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHired() {
            return hired;
        }

        public void setHired(String hired) {
            this.hired = hired;
        }

        public String getFired() {
            return fired;
        }

        public void setFired(String fired) {
            this.fired = fired;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}

