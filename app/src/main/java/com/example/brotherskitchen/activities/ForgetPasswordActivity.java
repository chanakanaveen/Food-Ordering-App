package com.example.brotherskitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.brotherskitchen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText emailReset;
    Button resetButton;
    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailReset = findViewById(R.id.email_login);
        resetButton = findViewById(R.id.send);
        progressBar = findViewById(R.id.progressbar);

        auth = FirebaseAuth.getInstance();
        
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailReset.getText().toString().trim();

        if (email.isEmpty()){
            emailReset.setError("Email is required!");
            emailReset.requestFocus();
            return;
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            emailReset.setError("Please provide valid email");
//            emailReset.requestFocus();
//            return;
//        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, "Try again! Something wrong happend!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}