package com.example.upriya.quoraclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button bLogout;
    TextView etWelcome;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bLogout = (Button) findViewById(R.id.bLogout);
        etWelcome = (TextView) findViewById(R.id.etWelcome);

        bLogout.setOnClickListener((View.OnClickListener) this);

        auth = FirebaseAuth.getInstance();



       if(auth.getCurrentUser() != null)
       {
           etWelcome.setText("Welcome, "+auth.getCurrentUser().getEmail());
       }


    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.bLogout)
            logoutUser();
    }

    private void logoutUser() {

        auth.signOut();
        if(auth.getCurrentUser() == null)
        {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }


    }


}
