package ru.job4j.serialization.json;

public class Contact {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }

    public String toXML() {
        return "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>"
                + System.lineSeparator()
                + "<contact phone=\"" + phone + "\">";
    }

    public String toXMLNoVersion() {
        return "<person phone=\"" + phone + "\"";
    }
}
