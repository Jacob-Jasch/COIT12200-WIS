package coit12200.wis.data;

import java.sql.Connection;
import java.util.List;

public class UserData {
    public record UserDetails(String user, String password){};
    Connection connection;

    public UserData() {

    }

    public void connect() {

    }

    public void disconnect() {

    }

//    public List<UserDetails> getUser(String n){
//
//    }
//
//    public int updatePassword(String n, String p){
//
//    }
}
