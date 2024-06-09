package com.itsc.ioc;

import java.sql.*;

public class StudentRepo {
    private String databaseName = "testDB";
    private String tableName = "student";
    private String database = "USE " + databaseName;
    private String createDB = "CREATE DATABASE " + databaseName;
    private String createTable = "CREATE TABLE " + tableName + " (id int auto_increment primary key, name varchar(255), email varchar(255))";
    
    private Connection connection = null;
    private Statement statement = null;
    
    public void createConnection() {
        String url = "jdbc:mysql://localhost:3306/?user=root";
        String username = "root";
        String password = "MySQL.P@5s_001!";
                
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void createDBAndTable() {
        try {
            createConnection();
            statement.executeUpdate(createDB);
            statement.executeUpdate(database);
            statement.executeUpdate(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public void insertIntoTable(Student student) {
        String insertData = "INSERT INTO " + tableName + " (name, email) VALUES (?, ?)";
        try {
            createConnection();
            statement.executeUpdate(database);
            PreparedStatement preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
