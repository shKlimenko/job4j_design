package jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(this.properties.getProperty("hibernate.connection.driver_class"));
        String url = this.properties.getProperty("hibernate.connection.url");
        String login = this.properties.getProperty("hibernate.connection.username");
        String password = this.properties.getProperty("hibernate.connection.password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        try (FileInputStream in = new FileInputStream("app.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            try (TableEditor tableEditor = new TableEditor(prop)) {

                tableEditor.createTable("store");
                System.out.println(getTableScheme(tableEditor.connection, "store"));

                tableEditor.addColumn("store", "engine", "varchar");
                System.out.println(getTableScheme(tableEditor.connection, "store"));

                tableEditor.addColumn("store", "gear", "varchar");
                System.out.println(getTableScheme(tableEditor.connection, "store"));

                tableEditor.renameColumn("store", "gear", "transmission");
                System.out.println(getTableScheme(tableEditor.connection, "store"));

                tableEditor.dropColumn("store", "engine");
                System.out.println(getTableScheme(tableEditor.connection, "store"));

                tableEditor.dropTable("store");
            }
        }
    }

    public void changeTable(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        changeTable(String.format(
                "create table if not exists %s();", tableName
        ));
    }

    public void dropTable(String tableName) throws Exception {
        changeTable(String.format(
                "drop table %s;", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        changeTable(String.format(
                "alter table %s add %s %s;", tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        changeTable(String.format(
                "alter table %s drop column %s;", tableName, columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        changeTable(String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        ));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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