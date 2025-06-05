package coit12200.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static coit12200.wis.roles.UserDataValidator.generateSHA1;

/**
 * UserData class handles database operations related to user data.
 * It connects to a MySQL database, retrieves user details, and updates passwords.
 * @author Jacob Duckworth
 */
public class UserData {
    public record UserDetails(String user, String password){};
    Connection connection;

    /**
     * Constructor for UserData class.
     */
    public UserData() {

    }

    /**
     * Attempts to connect to a MySQL database named "USERS"
     */
    public void connect() {
        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/USERS", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Disconnects from the MySQL database.
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves user details from the database based on the username.
     * @param n the username to search for
     * @return a list of UserDetails containing the username and password
     */
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

    /**
     * Updates the password for a given username in the database.
     * It hashes the new password using SHA-1 before updating.
     * @param n the username for which the password needs to be updated
     * @param p the new password to set
     * @return the number of rows affected by the update operation
     */
    public int updatePassword(String n, String p){
        int rowsAffected = 0;
        try {
            String hashedPassword = generateSHA1(p);
            String sql = "UPDATE PASSWORDS SET password = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, n);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in updatePassword: " + e.getMessage());
        }
        return rowsAffected;
    }
}
