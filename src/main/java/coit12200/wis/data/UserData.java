package coit12200.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    public record UserDetails(String user, String password){};
    Connection connection;

    public UserData() {

    }

    public void connect() {
        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/USERS", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<UserDetails> getUser(String n){
        List<UserDetails> users = new ArrayList<>();
        try {
            String sql = "SELECT username, password FROM PASSWORDS WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, n);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new UserDetails(username, password));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }

    public int updatePassword(String n, String p){
        int rowsAffected = 0;
        try {
            String sql = "UPDATE USERS SET password = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, n);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return rowsAffected;
    }
}
