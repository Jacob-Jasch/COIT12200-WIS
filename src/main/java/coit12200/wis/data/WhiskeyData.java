package coit12200.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WhiskeyData {
    public record WhiskeyDetails(String distillery, int age, String region, int price) { }
    private Connection connection;


    public WhiskeyData() {

    }

    public void connect(){
        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/WHISKEY", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
