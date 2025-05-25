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
        assertEquals("Password is incorrect.", vr.message());
    }

    @Test
    void checkCurrentDetailsUserNotFoundTest() {
        UserDataValidator validator = new UserDataValidator();
        UserData.UserDetails testUser = new UserData.UserDetails("admin", "password");

        ValidationResponse vr = validator.checkCurrentDetails(testUser, "wrong", "password");
        assertEquals("User not found.", vr.message());
    }

    @Test
    void checkCurrentDetailNullsUserTest() {
        UserDataValidator validator = new UserDataValidator();

        ValidationResponse vr = validator.checkCurrentDetails(null, "wrong", "password");
        assertFalse(vr.result());
        assertEquals("User not found.", vr.message());
    }

    @Test
    void checkCurrentDetailsNoUserProvidedTest() {
        UserDataValidator validator = new UserDataValidator();

        ValidationResponse vr = validator.checkCurrentDetails(null, null, null);
        assertFalse(vr.result());
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
}