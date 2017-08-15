package com.example.upriya.quoraclone;

import android.content.Intent;
import android.support.design.widget.Snackbar;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.upriya.quoraclone.R.layout.activity_register;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
     EditText etName;
     EditText etUsername;
     EditText etEmail;
     EditText etPassword;
     Button bRegister;
     private FirebaseAuth auth;
     android.support.design.widget.Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register);

         etName = (EditText) findViewById(R.id.etName);
         etUsername = (EditText) findViewById(R.id.etUsername);
         etEmail = (EditText) findViewById(R.id.etEmail);
         etPassword = (EditText) findViewById(R.id.etPassword);

         bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener((View.OnClickListener) this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view){

        if(view.getId() == R.id.bRegister){

            RegisterUser(etEmail.getText().toString(), etPassword.getText().toString());


        }

    }

    private void RegisterUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){

                           startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();

                        }

                        else {
                           startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            FirebaseUser user = auth.getCurrentUser();
                            user.sendEmailVerification();
                            Toast.makeText(getApplicationContext(), "Verification Email sent", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }
}
