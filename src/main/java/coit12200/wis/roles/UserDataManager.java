package coit12200.wis.roles;

import coit12200.wis.data.UserData;
import coit12200.wis.data.UserData.UserDetails;

import java.util.List;

/**
 * UserDataManager class manages user data operations.
 * It provides methods to find a user by name and update a user's password.
 * @author Jacob Duckworth
 */
public class UserDataManager {
    private UserData ud;

    /**
     * Constructor for UserDataManager class.
     * @param ud the UserData instance to manage user data operations
     */
    public UserDataManager(UserData ud) {
        this.ud = ud;
    }

    /**
     * Finds a user by their name.
     * @param name the name of the user to find
     * @return UserDetails if the user is found, otherwise null
     */
    public UserDetails findUser(String name){
        List<UserDetails> users = ud.getUser(name);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    /**
     * Updates the password for a user.
     * @param user the username of the user whose password is to be updated
     * @param password the new password to set for the user
     * @return the number of rows affected by the update operation
     */
    public int updatePassword(String user, String password){
        return ud.updatePassword(user, password);
    }
}
