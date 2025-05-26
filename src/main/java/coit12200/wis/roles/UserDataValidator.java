package coit12200.wis.roles;

import coit12200.wis.data.UserData.UserDetails;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDataValidator {
    private static final int MINIMUM_PASSWORD_LENGTH = 8;

    public static String generateSHA1(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest returns the hash value of the password as a
            // 20-byte array. The “encrypted/hash value” is always positive, so its
            // signum is 1.
            byte[] hashValue = md.digest(s.getBytes());
            int signum = 1;

            BigInteger value = new BigInteger(signum, hashValue);
            String hexDigits = value.toString(16);

            return hexDigits;
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        // never returned
        return null;
    }

    public UserDataValidator() {

    }

    public ValidationResponse checkCurrentDetails(UserDetails ud, String n, String oldp){
        if (ud == null || !ud.user().equals(n)) {
            return new ValidationResponse(false, "User not found.");
        }
        if (ud.password().equals(oldp)) {
            return new ValidationResponse(false, "User authenticated with default password. Please change your password.");
        }
        if (ud.password().equals(generateSHA1(oldp))){
            return new ValidationResponse(true, "User logged in with encrypted password");
        }
        return new ValidationResponse(false, "An unexpected error has occurred.");
    }

    public ValidationResponse checkNewDetails(UserDetails ud, String n, String oldp, String newp) {
        ValidationResponse currentCheck = checkCurrentDetails(ud, n, oldp);
        if (!currentCheck.result() && !currentCheck.message().contains("password")) {
            return currentCheck;
        }
        if (newp == null || newp.isBlank()) {
            return new ValidationResponse(false, "New password must be provided.");
        }
        if (newp.contains("\\") || newp.contains("\"") || newp.contains("'")) {
            return new ValidationResponse(false, "Password must not contain backslash or quotes.");
        }
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{" + MINIMUM_PASSWORD_LENGTH + ",}$";
        if (!newp.matches(pattern)) {
            return new ValidationResponse(false, "Password must be at least " + MINIMUM_PASSWORD_LENGTH
                    + " characters long, contain uppercase, lowercase, number, and special character.");
        }
        return new ValidationResponse(true, "Password is valid and ready to be updated.");
    }

    public ValidationResponse checkForFieldsPresent(String n, String p){
        if (n == null || n.isBlank() || p == null || p.isBlank()) {
            return new ValidationResponse(false, "Username and password must be provided.");
        }
        return new ValidationResponse(true, "Fields are present.");
    }

    public ValidationResponse checkForFieldsPresent(String n, String oldp, String newp) {
        if (n == null || n.isBlank() || oldp == null || oldp.isBlank() || newp == null || newp.isBlank()) {
            return new ValidationResponse(false, "All fields must be provided.");
        }
        return new ValidationResponse(true, "All required fields are present.");
    }
}
