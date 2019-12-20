package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilegameballcollector.validation.UserValidation;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(onClickListener);
        registerButton.setOnClickListener(onClickListener);
    }

    /*
    * Check if login button was pressed- try to log user,
    * else- register button was pressed open register activity
    */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loginButton) {
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MobileGameBallCollector", MODE_PRIVATE);

                String email = sharedPreferences.getString("email", "");
                String password = sharedPreferences.getString("password", "");

                UserValidation userValidation = new UserValidation(email, password, password);

                // Check if email or password from sharedPreferences are valid and if the input is the same as the one from sharedPreferences
                if (userValidation.isEverythingValid() && emailEditText.getText().toString().equalsIgnoreCase(email) && passwordEditText.getText().toString().equals(password)) {

                    Intent intent = new Intent
                            (LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong email or password or both email and password",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Intent intent = new Intent(
                        LoginActivity.this, RegistrationActivity.class);

                startActivity(intent);
            }
        }
    };

}
