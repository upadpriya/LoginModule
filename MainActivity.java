package com.example.upriya.quoraclone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.upriya.quoraclone.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LoginButton bFBLogin;
    EditText etEmail;
    EditText etPassword;
    Button bLogin;
    TextView tvRegister, tvForgotPass ;

    CallbackManager callbackManager;
    private FirebaseAuth auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(activity_main);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        bFBLogin = (LoginButton)findViewById(R.id.bFBLogin);
        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();
        bLogin.setOnClickListener(this);

        if(auth.getCurrentUser() != null){


            bFBLogin.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), R.string.com_facebook_loginview_cancel_action, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), R.string.com_facebook_image_download_unknown_error, Toast.LENGTH_SHORT).show();
                    }
                });


        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view){

        if(view.getId() == R.id.tvForgotPass){
            tvForgotPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v)
                {
                    Intent ForgotIntent = new Intent(MainActivity.this, Forgot.class);
                    MainActivity.this.startActivity(ForgotIntent);
                }
            });
        }

        else  if(view.getId() == R.id.tvRegister){
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                 public  void onClick(View v)
                {
                    Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                    MainActivity.this.startActivity(registerIntent);
                }
            });
        }

        else  if(view.getId() == R.id.bLogin){

           loginUser(etEmail.getText().toString(), etPassword.getText().toString());

        }


    }

    private void loginUser(String email, final String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){

                            startActivity(new Intent(MainActivity.this, MainActivity.class));

                        }

                        else {
                            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                        }

                    }
                });

    }
}