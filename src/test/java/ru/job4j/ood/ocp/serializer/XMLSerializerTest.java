package ru.job4j.ood.ocp.serializer;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLSerializerTest {

    @Test
    public void convertWithEmployeeReturnsValidXML() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Serializer xmlSerializer = new XMLSerializer();
        assertThat(xmlSerializer.convert(store.findBy(employee -> true)))
                .contains(String.format("<fired>%s</fired>", parser.parse(worker.getHired())))
                .contains(String.format("<hired>%s</hired>", parser.parse(worker.getFired())))
                .contains("<name>Ivan</name>")
                .contains("<salary>100.0</salary>");
    }

    @Test
    public void convertWithMultipleEmployeeReturnsValidXML() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Serializer xmlSerializer = new XMLSerializer();
        assertThat(xmlSerializer.convert(store.findBy(employee -> true)))
                .contains(String.format("<fired>%s</fired>", parser.parse(worker1.getHired())))
                .contains(String.format("<hired>%s</hired>", parser.parse(worker1.getFired())))
                .contains("<name>Ivan</name>")
                .contains("<salary>100.0</salary>")
                .contains(String.format("<fired>%s</fired>", parser.parse(worker2.getHired())))
                .contains(String.format("<hired>%s</hired>", parser.parse(worker2.getFired())))
                .contains("<name>Oleg</name>")
                .contains("<salary>200.0</salary>");
    }

    @Test
    public void convertWithEmptyListReturnsEmptyXML() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Serializer xmlSerializer = new XMLSerializer();
        assertThat(xmlSerializer.convert(store.findBy(employee -> true))).contains("<employees/>");
    }

    @Test
    public void convertWithNullReturnsExeption() {
        Serializer xmlSerializer = new XMLSerializer();
        assertThatThrownBy(() -> xmlSerializer.convert(null)).isInstanceOf(NullPointerException.class);
    }

}