package coit12200.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * WhiskeyData handles database operations related to Whiskey data.
 * It connects to a MySQL database, and retrieves whiskey details.
 * @author Jacob Duckworth
 */
public class WhiskeyData {
    /**
     * WhiskeyDetails is a record that holds information about a whiskey.
     * @param distillery the name of the distillery
     * @param age the age of the whiskey in years
     * @param region the region where the whiskey is produced
     * @param price the price of the whiskey in cents
     */
    public record WhiskeyDetails(String distillery, int age, String region, int price) { }
    private Connection connection;

    /**
     * Constructor for WhiskeyData class.
     */
    public WhiskeyData() {

    }

    /**
     * Attempts to connect to a MySQL database named "WHISKEY".
     */
    public void connect(){
        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/WHISKEY", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Disconnects from the MySQL database.
     */
    public void disconnect(){
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves all malts from the database.
     * Each malt is represented by a WhiskeyDetails record containing distillery, age, region, and price.
     * @return a list of WhiskeyDetails representing all malts
     */
    public List<WhiskeyDetails> getAllMalts(){
        List<WhiskeyDetails> allMalts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT distillery, age, region, price FROM SINGLEMALTS");
            while (resultSet.next()) {
                allMalts.add(new WhiskeyDetails(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allMalts;
    }

    /**
     * Retrieves malts from the database based on the specified region.
     * Each malt is represented by a WhiskeyDetails record containing distillery, age, region, and price.
     * @param r the region to filter malts by
     * @return a list of WhiskeyDetails representing malts from the specified region
     */
    public List<WhiskeyDetails> getMaltsFromRegion(String r){
        List<WhiskeyDetails> malts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                    "SELECT distillery, age, region, price FROM SINGLEMALTS WHERE region = ?");
            ps.setString(1, r);
            rs = ps.executeQuery();
            while (rs.next()) {
                malts.add(new WhiskeyDetails(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return malts;
    }

    /**
     * Retrieves malts from the database that fall within a specified age range.
     * Each malt is represented by a WhiskeyDetails record containing distillery, age, region, and price.
     * @param r1 the lower bound of the age range
     * @param r2 the upper bound of the age range
     * @return a list of WhiskeyDetails representing malts within the specified age range
     */
    public List<WhiskeyDetails> getMaltsInAgeRange(int r1, int r2){
        List<WhiskeyDetails> malts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                    "SELECT distillery, age, region, price FROM SINGLEMALTS WHERE age BETWEEN ? AND ?");
            ps.setInt(1, r1);
            ps.setInt(2, r2);
            rs = ps.executeQuery();
            while (rs.next()) {
                malts.add(new WhiskeyDetails(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return malts;
    }
}
