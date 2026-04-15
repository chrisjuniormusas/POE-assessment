package com.github.chrisjunior.loginapp;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Encapsulates registration and login behaviour for the application.
 *
 * <p>The method names are aligned to the task specification so that the code is easy
 * to mark and easy to unit test.
 */
public class Login {

    /**
     * Username rule from the task:
     * - must contain an underscore
     * - must be no more than five characters long
     */
    private static final int MAX_USERNAME_LENGTH = 5;

    /**
     * Password rule from the task:
     * - at least 8 characters
     * - at least 1 uppercase letter
     * - at least 1 digit
     * - at least 1 special character
     */
    private static final Pattern PASSWORD_COMPLEXITY_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$");

    /**
     * Regular expression for a South African cell number written with the international code.
     *
     * <p>Reference for the rule behind this pattern:
     * 1) South Africa uses country code +27 and national significant numbers of 9 digits
     *    (ITU Operational Bulletin, South Africa numbering plan).
     * 2) International numbers follow ITU-T E.164 presentation rules and start with '+'.
     *
     * Sources used when deriving this validation rule:
     * - https://www.itu.int/ITU-T/inr/nnp/
     * - https://www.itu.int/dms_pub/itu-t/opb/sp/T-SP-OB.898-2007-OAS-PDF-E.pdf
     * - https://www.itu.int/rec/T-REC-E.164/
     * - https://www.gov.za/sites/default/files/gcis_document/201409/22352.pdf
     *
     * <p>This pattern accepts +27 followed by exactly 9 digits, which gives an
     * international representation such as +27838968976.
     */
    private static final Pattern SA_CELL_PHONE_PATTERN = Pattern.compile("^\\+27\\d{9}$");

    private final User user;
    private boolean lastLoginSuccessful;

    public Login(User user) {
        this.user = Objects.requireNonNull(user, "user must not be null");
    }

    /**
     * Ensures that the username contains an underscore and is no more than five characters.
     *
     * @return true when the username satisfies both rules; otherwise false.
     */
    public boolean checkUserName() {
        String username = user.getUsername();
        return username != null
                && username.contains("_")
                && username.length() <= MAX_USERNAME_LENGTH;
    }

    /**
     * Ensures that the password satisfies the complexity requirements.
     *
     * @return true when the password matches the task rules; otherwise false.
     */
    public boolean checkPasswordComplexity() {
        String password = user.getPassword();
        return password != null && PASSWORD_COMPLEXITY_PATTERN.matcher(password).matches();
    }

    /**
     * Ensures that the cell phone number uses South Africa's international code and length.
     *
     * @return true when the number matches the required format; otherwise false.
     */
    public boolean checkCellPhoneNumber() {
        String cellPhoneNumber = user.getCellPhoneNumber();
        return cellPhoneNumber != null && SA_CELL_PHONE_PATTERN.matcher(cellPhoneNumber).matches();
    }

    /**
     * Provides the exact success or failure message for username validation.
     *
     * @return formatted message expected by the task.
     */
    public String getUsernameMessage() {
        if (checkUserName()) {
            return "Username successfully captured.";
        }

        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    /**
     * Provides the exact success or failure message for password validation.
     *
     * @return formatted message expected by the task.
     */
    public String getPasswordMessage() {
        if (checkPasswordComplexity()) {
            return "Password successfully captured.";
        }

        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    /**
     * Provides the exact success or failure message for cell phone validation.
     *
     * @return formatted message expected by the task's tests.
     */
    public String getCellPhoneMessage() {
        if (checkCellPhoneNumber()) {
            return "Cell number successfully captured.";
        }

        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    /**
     * Attempts to register the user.
     *
     * <p>The method returns the first failure message encountered. If all validations pass,
     * the method returns a combined success response so the calling code has a single,
     * easy-to-assert registration outcome.
     *
     * @return registration result message.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return getUsernameMessage();
        }

        if (!checkPasswordComplexity()) {
            return getPasswordMessage();
        }

        if (!checkCellPhoneNumber()) {
            return getCellPhoneMessage();
        }

        return "User has been registered successfully.";
    }

    /**
     * Verifies whether the supplied credentials match the stored credentials.
     *
     * @param enteredUsername username supplied during login.
     * @param enteredPassword password supplied during login.
     * @return true when both username and password match; otherwise false.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        lastLoginSuccessful = Objects.equals(user.getUsername(), enteredUsername)
                && Objects.equals(user.getPassword(), enteredPassword);
        return lastLoginSuccessful;
    }

    /**
     * Returns the required login status message after a login attempt.
     *
     * @return success or failure message.
     */
    public String returnLoginStatus() {
        if (lastLoginSuccessful) {
            return String.format(
                    "Welcoming %s, %s it is great to see you again.",
                    user.getFirstName(),
                    user.getLastName());
        }

        return "Username or password incorrect, please try again.";
    }
}
