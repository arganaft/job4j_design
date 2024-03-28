package ru.job4j.ood.ocp.serializer;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JSONSerializerTest {
    @Test
    void convertWithEmployeeReturnsValidJSON() {
        JSONSerializer jsonSerializer = new JSONSerializer();
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String expected = String.format("\"hired\": \"%s\"", parser.parse(worker.getFired()));
        assertThat(jsonSerializer.convert(store.findBy((employee -> true)))).contains(expected);
    }

    @Test
    void convertWithMultipleEmployeeReturnsValidJSON() {
        JSONSerializer jsonSerializer = new JSONSerializer();
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        assertThat(jsonSerializer.convert(store.findBy((employee -> true))))
                .contains(String.format("\"hired\": \"%s\"", parser.parse(worker1.getFired())))
                .contains(String.format("\"hired\": \"%s\"", parser.parse(worker2.getFired())));
    }

    @Test
    void convertWithEmptyListReturnsEmptyJSON() {
        JSONSerializer jsonSerializer = new JSONSerializer();
        MemoryStore store = new MemoryStore();
        assertThat(jsonSerializer.convert(store.findBy((employee -> true)))).isEqualTo("[]");
    }

    @Test
    void convertWithNullReturnsExeption() {
        JSONSerializer jsonSerializer = new JSONSerializer();
        assertThatThrownBy(() -> jsonSerializer.convert(null)).isInstanceOf(NullPointerException.class);
    }

}