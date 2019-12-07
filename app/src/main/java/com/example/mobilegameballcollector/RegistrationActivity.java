package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

                if (!email.isEmpty() && !password.isEmpty() &&
                        password.equals(password1)) {
                    SharedPreferences.Editor editor =
                            getSharedPreferences("MobileGameBallCollector", MODE_PRIVATE).edit();

                    editor.putString("email", email);
                    editor.putString("password", password);

                    editor.apply();
                    editor.commit();

                    Toast.makeText(RegistrationActivity.this, "Now you can login with your email and password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Empty email field or password fields are not the same",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
