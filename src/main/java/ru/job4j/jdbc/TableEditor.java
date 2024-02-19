package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    private Statement statement;

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);


        System.out.println("create Table");
        String tableName = "JDBS_Table";
        tableEditor.createTable(tableName);
        System.out.println(tableEditor.getTableScheme(tableName));


        System.out.println("add Column");
        String columnName = "customer";
        String columnType = "varchar(255)";
        tableEditor.addColumn(tableName, columnName, columnType);
        System.out.println(tableEditor.getTableScheme(tableName));


        System.out.println("rename column");
        String newColumnName = "buyer";
        tableEditor.renameColumn(tableName, columnName, newColumnName);
        System.out.println(tableEditor.getTableScheme(tableName));


        System.out.println("drop Column");
        tableEditor.dropColumn(tableName, newColumnName);
        System.out.println(tableEditor.getTableScheme(tableName));


        System.out.println(String.format("dropTable %s", tableName));
        tableEditor.dropTable(tableName);

    }

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
        statement = connection.createStatement();
    }

    private void initConnection() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        statement.executeUpdate(
                String.format("CREATE TABLE IF NOT EXISTS %s(id serial primary key);", tableName)
        );
    }

    public void dropTable(String tableName) throws SQLException {
        statement.executeUpdate(
                String.format("DROP TABLE IF EXISTS %s;", tableName)
        );
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        statement.executeUpdate(
                String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type)
        );
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        statement.executeUpdate(
                String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName)
        );
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        statement.executeUpdate(
                String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName)
        );

    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
