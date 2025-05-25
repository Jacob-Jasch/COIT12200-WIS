package coit12200.wis.roles;

import coit12200.wis.data.UserData;
import coit12200.wis.data.UserData.UserDetails;

import java.util.List;

public class UserDataManager {
    private UserData ud;

    public UserDataManager(UserData ud) {
        this.ud = ud;
    }

    public UserDetails findUser(String name){
        List<UserDetails> users = ud.getUser(name);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public int updatePassword(String user, String password){
        return ud.updatePassword(user, password);
    }
}
