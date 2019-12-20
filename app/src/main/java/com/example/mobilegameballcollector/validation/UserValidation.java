package com.example.mobilegameballcollector.validation;

public class UserValidation {

    private String email;
    private String password1;
    private String password2;

    public UserValidation(String email, String password1, String password2) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }

    private boolean isEmailValid(String email) {
        if (email == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password1, String password2) {
        if (password1.length() < 1 || password2.length() < 1 || !password1.equals(password2))
            return false;

        return true;
    }

    public boolean isEverythingValid() {
        return isEmailValid(this.email) && isPasswordValid(this.password1, this.password2);
    }

}
