package com.example.android.sociohack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText email,pass,repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);




    }

    public void CreateAcc(View view){
        String username = email.getText().toString();
        String password = pass.getText().toString();
        String repassword = repass.getText().toString();
        String type = "create";

        if(password.equals(repassword)&&(password!=null)) {

            BackgroundWorker_Create backgroundWorker = new BackgroundWorker_Create(this);
            backgroundWorker.execute(type, username, password);
            Register.this.finish();
        }else{
            Toast.makeText(Register.this,"Passwords doesn't match!",Toast.LENGTH_LONG).show();
        }


    }
}
