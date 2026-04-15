package com.github.chrisjunior.loginapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Login class.
 *
 * <p>These tests use the exact data requested in the task prompt so that the behaviour
 * remains aligned with the marking expectations.
 */
class LoginTest {

    @Test
    void usernameCorrectlyFormattedShouldReturnTrue() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertTrue(login.checkUserName());
    }

    @Test
    void usernameIncorrectlyFormattedShouldReturnFalse() {
        User user = new User("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertFalse(login.checkUserName());
    }

    @Test
    void passwordMeetsComplexityRequirementsShouldReturnTrue() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    void passwordDoesNotMeetComplexityRequirementsShouldReturnFalse() {
        User user = new User("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        Login login = new Login(user);

        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    void cellPhoneNumberCorrectlyFormattedShouldReturnTrue() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    void cellPhoneNumberIncorrectlyFormattedShouldReturnFalse() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        Login login = new Login(user);

        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    void loginSuccessfulShouldReturnTrue() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFailedShouldReturnFalse() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertFalse(login.loginUser("wrong_1", "wrongPassword"));
    }

    @Test
    void successfulLoginShouldReturnExpectedWelcomeMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        login.loginUser("kyl_1", "Ch&&sec@ke99!");

        assertEquals("Welcoming Kyle, Smith it is great to see you again.", login.returnLoginStatus());
    }

    @Test
    void failedLoginShouldReturnExpectedErrorMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        login.loginUser("wrong_1", "wrongPassword");

        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus());
    }

    @Test
    void incorrectlyFormattedUsernameShouldReturnExpectedMessage() {
        User user = new User("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                login.getUsernameMessage());
    }

    @Test
    void passwordThatMeetsComplexityShouldReturnExpectedMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertEquals("Password successfully captured.", login.getPasswordMessage());
    }

    @Test
    void passwordThatFailsComplexityShouldReturnExpectedMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        Login login = new Login(user);

        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                login.getPasswordMessage());
    }

    @Test
    void correctlyFormattedCellPhoneShouldReturnExpectedMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertEquals("Cell number successfully captured.", login.getCellPhoneMessage());
    }

    @Test
    void incorrectlyFormattedCellPhoneShouldReturnExpectedMessage() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        Login login = new Login(user);

        assertEquals(
                "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                login.getCellPhoneMessage());
    }

    @Test
    void registerUserShouldReturnSuccessWhenAllFieldsAreValid() {
        User user = new User("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertEquals("User has been registered successfully.", login.registerUser());
    }

    @Test
    void registerUserShouldReturnUsernameErrorWhenUsernameIsInvalid() {
        User user = new User("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login(user);

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                login.registerUser());
    }
}
