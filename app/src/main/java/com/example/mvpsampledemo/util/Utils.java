package com.example.mvpsampledemo.util;

import static android.util.Patterns.EMAIL_ADDRESS;


public class Utils {
    public static boolean isEmailValid(String email) {
        if (!email.isEmpty() && EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }
}
