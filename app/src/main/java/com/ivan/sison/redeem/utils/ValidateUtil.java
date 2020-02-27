package com.ivan.sison.redeem.utils;

public class ValidateUtil {

    public static boolean isUsernameValid(String username) {
        if (username.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean isPasswordValid(String pass) {
        if (pass.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean isTicketIdValid(String ticketId) {
        if (ticketId.isEmpty()) {
            return false;
        }

        return true;
    }
}
