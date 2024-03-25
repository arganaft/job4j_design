package ru.job4j.ood.ocp.serializer;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface Serializer {
    String convert(List<Employee> report) throws JAXBException;
}
