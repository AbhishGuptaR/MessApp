package com.example.android.sociohack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class LoginScreen extends AppCompatActivity  {
    EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

         email = (EditText) findViewById(R.id.emailfield);
         pass = (EditText) findViewById(R.id.passwordfield);
         TextView create  = (TextView) findViewById(R.id.CreateAcc);
        TextView report  = (TextView) findViewById(R.id.report);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginScreen.this,Register.class);
                startActivity(i);


            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts( "mailto","abhishekguptaa3@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Error report");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Type your problem here..");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });


    }

    public void onLogin(View view){
        String username = email.getText().toString();
        String password = pass.getText().toString();
        if(username.equals("admin")&&(password.equals("admin"))){
           Intent i = new Intent(this,AdminLanding.class);
           startActivity(i);
        }
        else {
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
            LoginScreen.this.finish();
        }
    }






}
