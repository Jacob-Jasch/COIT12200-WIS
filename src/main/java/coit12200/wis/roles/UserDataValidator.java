package coit12200.wis.roles;

import coit12200.wis.data.UserData.UserDetails;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserDataValidator class is responsible for validating user data such as usernames and passwords.
 * It provides methods to generate SHA-1 hashes, check current and new user details,
 * @author Jacob Duckworth
 */
public class UserDataValidator {
    /** Minimum password length required for validation. */
    private static final int MINIMUM_PASSWORD_LENGTH = 8;

    /**
     * Generates a SHA-1 hash of the provided string.
     * @param s the string to be hashed
     * @return the SHA-1 hash of the string
     */
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

    /**
     * Constructor for UserDataValidator.
     */
    public UserDataValidator() {

    }

    /**
     * Checks the current user details against the provided username and old password.
     * It validates if the user exists, if the password matches the old password,
     * @param ud the UserDetails object containing user information
     * @param n the username to check
     * @param oldp the old password to check
     * @return ValidationResponse indicating the result of the validation
     */
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

    /**
     * Checks the new user details against the provided username, old password, and new password.
     * It validates if the user exists, if the old password matches, and if the new password meets the criteria.
     * @param ud the UserDetails object containing user information
     * @param n the username to check
     * @param oldp the old password to check
     * @param newp the new password to validate
     * @return ValidationResponse indicating the result of the validation
     */
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

    /**
     * Checks if the username and password fields are present.
     * It validates that both fields are not null or blank.
     * @param n the username to check
     * @param p the password to check
     * @return ValidationResponse indicating the result of the validation
     */
    public ValidationResponse checkForFieldsPresent(String n, String p){
        if (n == null || n.isBlank() || p == null || p.isBlank()) {
            return new ValidationResponse(false, "Username and password must be provided.");
        }
        return new ValidationResponse(true, "Fields are present.");
    }

    /**
     * Checks if the username, old password, and new password fields are present.
     * It validates that all three fields are not null or blank.
     * @param n the username to check
     * @param oldp the old password to check
     * @param newp the new password to check
     * @return ValidationResponse indicating the result of the validation
     */
    public ValidationResponse checkForFieldsPresent(String n, String oldp, String newp) {
        if (n == null || n.isBlank() || oldp == null || oldp.isBlank() || newp == null || newp.isBlank()) {
            return new ValidationResponse(false, "All fields must be provided.");
        }
        return new ValidationResponse(true, "All required fields are present.");
    }
}
