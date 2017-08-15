package com.example.upriya.quoraclone;

import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Forgot extends AppCompatActivity implements View.OnClickListener {
    Button bChangePass;
    EditText etEmail;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        bChangePass = (Button) findViewById(R.id.bChangePass);
        etEmail = (EditText) findViewById(R.id.etEmail);


        bChangePass.setOnClickListener((View.OnClickListener) this);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.bChangePass)
        {
            SendEmail(etEmail.getText().toString());
        }
    }

    private void SendEmail(String email) {

            auth.sendPasswordResetEmail(email);
            startActivity(new Intent(Forgot.this, MainActivity.class));
        Toast.makeText(getApplicationContext(), "Email has been sent", Toast.LENGTH_SHORT).show();


    }
}
