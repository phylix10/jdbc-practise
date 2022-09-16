package com.alireza.repository;

import java.sql.*;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.User;

public class UserRepository {
    private static final String INSERT_QUERY =
            "insert into tbl_user (first_name,last_name,age,national_code,birth_date) VALUES (?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update tbl_user set first_name = ? , age = ? where id = ?";
    private static final String SELECT_BY_ID_QUERY = "select * from tbl_user where id = ?";
    private static final String DELETE_BY_ID_QUERY = "delete from tbl_user where id = ?";

    public static void createUser(User user) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getNationalCode());
            preparedStatement.setDate(5, user.getBirthDate());

            preparedStatement.execute();
            System.out.println("add 1 user was done successfully");

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println("number of rows was updated: " + result);

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void selectUserById(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print("id: " + resultSet.getInt("id"));
                System.out.print(", firstName: " + resultSet.getString("first_name"));
                System.out.print(", lastName: " + resultSet.getString("last_name"));
                System.out.print(", age: " + resultSet.getInt("age"));
                System.out.print(", nationalCode: " + resultSet.getString("national_code"));
                System.out.println(", birthDate: " + resultSet.getDate("birth_date"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void deleteUserById(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
            System.out.println("The number of users removed from the table: " + result);

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
