package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRSortedReport implements Report {
    private final Store store;

    public HRSortedReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        if (filter == null) {
            throw new NullPointerException("передано null значение");
        }
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());

        List<Employee> sortedEmployee = store.findBy(filter);
        sortedEmployee.sort(sortBySalaryDescending());

        for (Employee employee : sortedEmployee) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private Comparator<Employee> sortBySalaryDescending() {
        return (emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}
