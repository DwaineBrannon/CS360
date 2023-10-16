package com.projects.dwainebrannoninventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        EditText passwordEditText =(EditText) findViewById(R.id.password_edit_text);
        Button signUpButton = (Button) findViewById(R.id.signup_button);
        Button loginButton = (Button) findViewById(R.id.login_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase userDatabase = new UserDatabase(getApplicationContext());
                userDatabase.addUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase userDatabase = new UserDatabase(getApplicationContext());
                boolean userExists = userDatabase.checkUserCredentials(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if (userExists) {
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "User not registered", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase userDatabase = new UserDatabase(MainActivity.this);
                boolean userExists = userDatabase.checkUserCredentials(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if (userExists) {
                    Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                } else {
                    userDatabase.addUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                    Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    }

