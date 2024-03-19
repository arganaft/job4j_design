package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class HRSortedReportTest {

    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee poorWorker = new Employee("Ivan", now, now, 100);
        Employee richWorker = new Employee("Oleg", now, now, 1000);
        store.add(poorWorker);
        store.add(richWorker);
        Report engine = new HRSortedReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(richWorker.getName()).append(" ")
                .append(richWorker.getSalary())
                .append(System.lineSeparator())
                .append(poorWorker.getName()).append(" ")
                .append(poorWorker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenNullThenExeption() {
        MemoryStore store = new MemoryStore();
        Report engine = new HRSortedReport(store);
        assertThatThrownBy(() -> engine.generate(null))
                .isInstanceOf(NullPointerException.class);
    }
}