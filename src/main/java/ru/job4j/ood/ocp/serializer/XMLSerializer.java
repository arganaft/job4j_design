package ru.job4j.ood.ocp.serializer;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class XMLSerializer implements Serializer {
    @Override
    public String convert(List<Employee> report) throws JAXBException {
        if (report == null) {
            throw new NullPointerException("передано null значение");
        }
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(report), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

}
