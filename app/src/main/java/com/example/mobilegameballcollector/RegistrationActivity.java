package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mobilegameballcollector.validation.UserValidation;

public class RegistrationActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordEditText1;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String password1 = passwordEditText1.getText().toString();

                // Check if email is valid and passwords fields are not empty and also if value of both password fields is the same
                UserValidation userValidation = new UserValidation(email, password, password1);

                if (userValidation.isEverythingValid()) {
                    SharedPreferences.Editor sharedPreferencesEditor =
                            getSharedPreferences("MobileGameBallCollector", MODE_PRIVATE).edit();

                    sharedPreferencesEditor.putString("email", email);
                    sharedPreferencesEditor.putString("password", password);

                    sharedPreferencesEditor.apply();
                    sharedPreferencesEditor.commit();

                    // Clear texts
                    emailEditText.getText().clear();
                    passwordEditText.getText().clear();
                    passwordEditText1.getText().clear();

                    Toast.makeText(RegistrationActivity.this, "Now you can login with your email and password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Invalid email field or password fields are not the same or length of password is less than 1",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
