package com.example.cst8334project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst8334project.authentication.AuthenticationManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText edit_username = findViewById(R.id.edit_username);
        final EditText edit_pass = findViewById(R.id.edit_pass);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                String username = edit_username.getText().toString();
                String password = edit_pass.getText().toString();
                authenticate(username, password);
            }
        });
    }

    private void authenticate(String username, String password) {
        if (AuthenticationManager.authenticate(username, password)) {
            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "The username or password you entered was incorrect", Toast.LENGTH_SHORT).show();
        }
    }

}





