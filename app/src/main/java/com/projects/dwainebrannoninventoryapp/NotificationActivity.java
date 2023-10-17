package com.projects.dwainebrannoninventoryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private LinearLayout notificationSection;
    private Button requestPermissionButton;
    private EditText phoneNumberEditText;
    private TextView successMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_screen);

        notificationSection = findViewById(R.id.notificationSection);
        requestPermissionButton = findViewById(R.id.requestPermissionButton);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        successMessage = findViewById(R.id.successMessage);

        requestPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the phone number input field
                notificationSection.setVisibility(View.VISIBLE);
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Check if the user entered a phone number
                if (!phoneNumber.isEmpty()) {
                    // Display the "Success" message
                    successMessage.setText("Success");

                    finish();
                }
            }
        });
    }
}
