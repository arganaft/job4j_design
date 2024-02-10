package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Config sqlProperties = new Config("./data/app.properties");
        sqlProperties.load();
        String url = sqlProperties.value("url");
        String login = sqlProperties.value("login");
        String password = sqlProperties.value("password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());

        }
    }
}
