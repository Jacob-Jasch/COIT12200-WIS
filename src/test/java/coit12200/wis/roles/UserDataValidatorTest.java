package coit12200.wis.roles;

import coit12200.wis.data.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDataValidatorTest {

    @Test
    void checkCurrentDetailsValidTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails testUser = new UserData.UserDetails("admin", "password");

        ValidationResponse vr = validator.checkCurrentDetails(testUser, "admin", "password");
        assertEquals("User authenticated with default password. Please change your password.", vr.message());
    }

    @Test
    void checkCurrentDetailsWrongPasswordTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails testUser = new UserData.UserDetails("admin", "password");

        ValidationResponse vr = validator.checkCurrentDetails(testUser, "admin", "wrong");
        assertEquals("An unexpected error has occurred.", vr.message());
    }

    @Test
    void checkCurrentDetailsUserNotFoundTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails testUser = new UserData.UserDetails("admin", "password");

        ValidationResponse vr = validator.checkCurrentDetails(testUser, "wrong", "password");
        assertEquals("User not found.", vr.message());
    }

    @Test
    void checkCurrentDetailsNoUserProvidedTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkCurrentDetails(null, "wrong", "password");
        assertEquals("User not found.", vr.message());
    }

    @Test
    void checkCurrentDetailNullsUserTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkCurrentDetails(null, null, null);
        assertEquals("User not found.", vr.message());
    }



    @Test
    void checkForFieldsPresentBothEmptyTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("", "");
        assertEquals("Username and password must be provided.", vr.message());
    }

    @Test
    void checkForFieldsPresentOneEmptyTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("user", "");
        assertEquals("Username and password must be provided.", vr.message());
    }

    @Test
    void checkForFieldsPresentCorrectTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("user", "password");
        assertEquals("Fields are present.", vr.message());
    }

    @Test
    void checkForFieldsPresentWhitespaceOnlyTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent(" ", " ");
        assertEquals("Username and password must be provided.", vr.message());
    }

    @Test
    void checkForFieldsPresentNullInputTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent(null, null);
        assertEquals("Username and password must be provided.", vr.message());
    }


    @Test
    void checkForFieldsPresentAllEmptyTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("", "", "");
        assertEquals("All fields must be provided.", vr.message());
    }

    @Test
    void checkForFieldsPresentSomeEmptyTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("user", "", "newpass");
        assertEquals("All fields must be provided.", vr.message());
    }

    @Test
    void checkForFieldsPresentAllValidTest() {
        UserDataValidator validator = new UserDataValidator();
        ValidationResponse vr = validator.checkForFieldsPresent("user", "oldpass", "newpass");
        assertEquals("All required fields are present.", vr.message());
    }



    @Test
    void newPasswordMissingUppercaseTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails user = new UserData.UserDetails("user", "password");

        ValidationResponse result = validator.checkNewDetails(user, "user", "password", "password1!");
        assertEquals("Password must be at least 8 characters long, contain uppercase, lowercase, number, and special character.", result.message());
    }

    @Test
    void newPasswordContainsQuoteTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails user = new UserData.UserDetails("user", "password");

        ValidationResponse result = validator.checkNewDetails(user, "user", "password", "GoodPass1'");
        assertEquals("Password must not contain backslash or quotes.", result.message());
    }

    @Test
    void newPasswordValidTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails user = new UserData.UserDetails("user", "password");

        ValidationResponse result = validator.checkNewDetails(user, "user", "password", "Valid1@Pass");
        assertEquals("Password is valid and ready to be updated.", result.message());
    }

    @Test
    void newPasswordNullTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails user = new UserData.UserDetails("user", "password");

        ValidationResponse result = validator.checkNewDetails(user, null, null, null);
        assertEquals("User not found.", result.message());
    }
}