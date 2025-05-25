package coit12200.wis.roles;

import coit12200.wis.data.UserData.UserDetails;

public class UserDataValidator {
    private static final int MINIMUM_PASSWORD_LENGTH = 8;

//    public static String generateSHA1(String s){
//
//    }

    public UserDataValidator() {

    }

    public ValidationResponse checkCurrentDetails(UserDetails ud, String n, String oldp){
        if (ud == null) {
            return new ValidationResponse(false, "User not found.");
        }
        if (ud == null || !ud.user().equals(n)) {
            return new ValidationResponse(false, "User not found.");
        }
        if (!"password".equals(oldp)) {
            return new ValidationResponse(false, "Password is incorrect.");
        }
        if (ud.password().equals(oldp)) {
            return new ValidationResponse(false, "User authenticated with default password. Please change your password.");
        }
        return new ValidationResponse(true, "An unexpected error has occurred.");
    }

//    public ValidationResponse checkNewDetails(UserDetails ud, String n, String oldp, String newp){
//
//    }

    public ValidationResponse checkForFieldsPresent(String n, String p){
        if (n == null || n.isBlank() || p == null || p.isBlank()) {
            return new ValidationResponse(false, "Username and password must be provided.");
        }
        return new ValidationResponse(true, "Fields are present.");
    }

//    public ValidationResponse checkForFieldsPresent(String n, String oldp, String newp){
//
//    }
}
