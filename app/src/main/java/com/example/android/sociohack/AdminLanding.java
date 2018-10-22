package com.example.android.sociohack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);

        Button addFood = (Button) findViewById(R.id.adminaddfood);
        Button setMenu = (Button) findViewById(R.id.adminsetmenu);
        Button editFood = (Button) findViewById(R.id.admineditfood);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminAdd.class);
                startActivity(i);
            }
        });

        setMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        });

        editFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminEdit.class);
                startActivity(i);
            }
        });

    }
}
