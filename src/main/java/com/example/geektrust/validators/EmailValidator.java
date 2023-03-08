package com.example.geektrust.validators;

import java.util.regex.Pattern;

public class EmailValidator {
    public static final Pattern validEmailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static Boolean validate(String email) {
        return validEmailPattern.matcher(email).matches();
    }
}
